package ai.shreds.wordpress4j.userRetrieval.domain.services;

import ai.shreds.wordpress4j.userRetrieval.application.dtos.UserDTO;
import ai.shreds.wordpress4j.userRetrieval.domain.entities.OptionEntity;
import ai.shreds.wordpress4j.userRetrieval.domain.entities.UserEntity;
import ai.shreds.wordpress4j.userRetrieval.domain.entities.UserMetaEntity;
import ai.shreds.wordpress4j.userRetrieval.domain.exceptions.UserNotFoundException;
import ai.shreds.wordpress4j.userRetrieval.domain.exceptions.InvalidUserIdException;
import ai.shreds.wordpress4j.userRetrieval.domain.exceptions.DatabaseTimeoutException;
import ai.shreds.wordpress4j.userRetrieval.domain.ports.GravatarServicePort;
import ai.shreds.wordpress4j.userRetrieval.domain.ports.OptionRepositoryPort;
import ai.shreds.wordpress4j.userRetrieval.domain.ports.UserRepositoryPort;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepositoryPort userRepositoryPort;
    private final OptionRepositoryPort optionRepositoryPort;
    private final GravatarServicePort gravatarServicePort;

    public UserService(UserRepositoryPort userRepositoryPort, OptionRepositoryPort optionRepositoryPort, GravatarServicePort gravatarServicePort) {
        this.userRepositoryPort = userRepositoryPort;
        this.optionRepositoryPort = optionRepositoryPort;
        this.gravatarServicePort = gravatarServicePort;
    }

    @Transactional
    public List<UserDTO> listUsers() throws DataAccessException, DatabaseTimeoutException {
        try {
            OptionEntity siteurl = optionRepositoryPort.findByOptionName("siteurl");

            return userRepositoryPort.findAllUsers().stream()
                    .map(user -> new UserDTO(
                            user.getId(),
                            user.getDisplayName(),
                            user.getUserUrl(),
                            "", // Description is typically fetched from user meta
                            siteurl.getOptionValue() + "/author/" + user.getUserNicename() + "/",
                            user.getUserNicename(),
                            generateAvatarUrls(user.getUserEmail()),
                            userRepositoryPort.findUserMetadataByUserId(user.getId())
                                    .stream()
                                    .collect(Collectors.groupingBy(UserMetaEntity::getMetaKey, Collectors.mapping(UserMetaEntity::getMetaValue, Collectors.toList())))
                                    .entrySet().stream()
                                    .map(entry -> Map.of(entry.getKey(), entry.getValue().get(0)))
                                    .collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseTimeoutException("Database timeout occurred while fetching users.");
        }
    }

    @Transactional
    public UserDTO getUserById(Long userId) throws DataAccessException, UserNotFoundException, InvalidUserIdException, DatabaseTimeoutException {
        if (userId == null || userId <= 0) {
            throw new InvalidUserIdException("Invalid user ID provided.");
        }

        Optional<UserEntity> userEntity = userRepositoryPort.findUserById(userId);
        if (userEntity.isEmpty()) {
            throw new UserNotFoundException("User not found for ID: " + userId);
        }

        OptionEntity siteurl = optionRepositoryPort.findByOptionName("siteurl");

        UserEntity user = userEntity.get();
        return new UserDTO(
                user.getId(),
                user.getDisplayName(),
                user.getUserUrl(),
                "", // Description is typically fetched from user meta
                siteurl.getOptionValue() + "/author/" + user.getUserNicename() + "/",
                user.getUserNicename(),
                generateAvatarUrls(user.getUserEmail()),
                userRepositoryPort.findUserMetadataByUserId(user.getId())
                        .stream()
                        .collect(Collectors.groupingBy(UserMetaEntity::getMetaKey, Collectors.mapping(UserMetaEntity::getMetaValue, Collectors.toList())))
                        .entrySet().stream()
                        .map(entry -> Map.of(entry.getKey(), entry.getValue().get(0)))
                        .collect(Collectors.toList())
        );
    }

    private Map<String, String> generateAvatarUrls(String email) {
        if (!gravatarServicePort.validateEmailFormat(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        String md5Hash = gravatarServicePort.computeMd5Hash(email);
        return Map.of(
                "24", gravatarServicePort.generateGravatarUrl(md5Hash, 24),
                "48", gravatarServicePort.generateGravatarUrl(md5Hash, 48),
                "96", gravatarServicePort.generateGravatarUrl(md5Hash, 96)
        );
    }
}