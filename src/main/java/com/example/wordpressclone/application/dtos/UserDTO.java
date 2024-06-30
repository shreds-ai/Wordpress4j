package com.example.wordpressclone.application.dtos;

import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.Email;

/**
 * Data Transfer Object used to transfer user data between different layers of the application,
 * particularly from the domain layer to the API layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    @URL
    private String url;
    private String description;
    private String link;
    private String slug;
    private Map<String, String> avatarUrls;
    private List<Map<String, String>> meta;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", slug='" + slug + '\'' +
                ", avatarUrls=" + avatarUrls +
                ", meta=" + meta +
                '}';
    }

    public void addMetadata(String key, String value) {
        if (this.meta != null) {
            this.meta.add(Map.of(key, value));
        }
    }

    public void computeAvatarUrls(String email) {
        String hash = DigestUtils.md5Hex(email);
        this.avatarUrls = Map.of(
            "24", "http://2.gravatar.com/avatar/" + hash + "?s=24&d=mm&r=g",
            "48", "http://2.gravatar.com/avatar/" + hash + "?s=48&d=mm&r=g",
            "96", "http://2.gravatar.com/avatar/" + hash + "?s=96&d=mm&r=g"
        );
    }
}