package com.example.wordpressclone.application.use_cases;

import com.example.wordpressclone.application.dtos.UserDTO;
import com.example.wordpressclone.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;

/**
 * Use case class for handling user-related operations. It coordinates actions between the domain and application layers, ensuring that data flows correctly through services and ports.
 */
@Component
public class UserUseCase {

    private final UserService userService;

    /**
     * Constructor for dependency injection.
     *
     * @param userService The user service to be injected.
     */
    @Autowired
    public UserUseCase(UserService userService) {
        this.userService = userService;
    }

    /**
     * Fetches a single user by their ID with caching.
     *
     * @param userId The ID of the user to fetch.
     * @return UserDTO containing user details.
     * @throws Exception if there is an error during the operation.
     */
    @Cacheable("users")
    public UserDTO getUserById(Long userId) throws Exception {
        Objects.requireNonNull(userId, "User ID must not be null");
        return userService.getUserById(userId);
    }

    /**
     * Retrieves all users from the database with caching.
     *
     * @return List of UserDTOs containing user details.
     * @throws Exception if there is an error during the operation.
     */
    @Cacheable("allUsers")
    public List<UserDTO> listUsers() throws Exception {
        return userService.listUsers();
    }
}