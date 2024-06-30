package com.example.wordpressclone.application.dtos;

import com.example.wordpressclone.domain.entities.UserEntity;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String url;
    private String description;
    private String link;
    private String slug;
    private Map<String, String> avatarUrls;
    private List<Map<String, String>> meta;

    public UserEntity toUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(this.id);
        userEntity.setDisplayName(this.name);
        userEntity.setUserUrl(this.url);
        userEntity.setDescription(this.description);
        userEntity.setUserNicename(this.slug);
        return userEntity;
    }

    public static UserResponseDTO fromUserEntity(UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("User entity cannot be null");
        }
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getDisplayName());
        dto.setUrl(user.getUserUrl());
        dto.setDescription(user.getDescription());
        dto.setLink("http://localhost:8090/author/" + user.getUserNicename() + "/");
        dto.setSlug(user.getUserNicename());
        dto.setAvatarUrls(generateGravatarUrls(user.getUserEmail()));
        dto.setMeta(mapUserMeta(user.getMeta()));
        return dto;
    }

    private static Map<String, String> generateGravatarUrls(String email) {
        if (email == null || !email.contains("@")) {
            return Map.of("24", "default_url_24", "48", "default_url_48", "96", "default_url_96");
        }
        return Map.of(
            "24", "http://gravatar.com/avatar/" + email + "?s=24",
            "48", "http://gravatar.com/avatar/" + email + "?s=48",
            "96", "http://gravatar.com/avatar/" + email + "?s=96"
        );
    }

    private static List<Map<String, String>> mapUserMeta(List<Map<String, String>> meta) {
        if (meta == null || meta.isEmpty()) {
            throw new IllegalArgumentException("Metadata cannot be null or empty");
        }
        return meta;
    }

    public boolean validateData() {
        return id != null && name != null && !name.isEmpty() && url != null && !url.isEmpty();
    }
}