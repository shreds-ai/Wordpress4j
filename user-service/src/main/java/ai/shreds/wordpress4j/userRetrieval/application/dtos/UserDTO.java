package ai.shreds.wordpress4j.userRetrieval.application.dtos;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object used to transfer user data between different layers of the application,
 * particularly from the domain layer to the API layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotEmpty
    @JsonProperty("name")
    private String name;

    @URL
    @JsonProperty("url")
    private String url;

    @JsonProperty("description")
    private String description;

    @JsonProperty("link")
    private String link;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("avatar_urls")
    private Map<String, String> avatarUrls;

    @JsonProperty("meta")
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