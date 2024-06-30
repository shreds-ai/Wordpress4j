package com.example.wordpressclone.application.dtos;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object used to transfer user data between different layers of the application,
 * particularly from the domain layer to the API layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @URL
    private String url;
    private String description;
    private String link;
    private String slug;
    private Map<String, String> avatarUrls;
    private List<Map<String, String>> meta;

    public void addMetadata(String key, String value) {
        if (this.meta != null) {
            this.meta.add(Map.of(key, value));
        }
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", slug='" + slug + '\'' +
                ", avatarUrls='" + avatarUrls + '\'' +
                ", meta='" + meta + '\'' +
                '}';
    }
}