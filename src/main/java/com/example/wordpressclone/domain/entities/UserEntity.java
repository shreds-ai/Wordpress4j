package com.example.wordpressclone.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;
import java.util.ArrayList;
import com.example.wordpressclone.application.dtos.UserDTO;

/**
 * Represents the User entity correlating to the 'wp_users' table in the WordPress database.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "wp_users")
public class UserEntity {

    @Id
    @NotNull
    @Column(name = "ID")
    private Long id;

    @NotEmpty
    @Column(name = "user_login")
    private String userLogin;

    @NotEmpty
    @Column(name = "user_nicename")
    private String userNicename;

    @NotEmpty
    @Column(name = "user_url")
    private String userUrl;

    @Email
    @NotEmpty
    @Column(name = "user_email")
    private String userEmail;

    @NotEmpty
    @Column(name = "display_name")
    private String displayName;

    /**
     * Converts this entity to a UserDTO object.
     * @return UserDTO corresponding to this UserEntity.
     */
    public UserDTO toUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setId(this.id);
        dto.setName(this.displayName);
        dto.setUrl(this.userUrl);
        dto.setDescription("Description based on user meta");
        dto.setLink("http://localhost:8090/author/" + this.userNicename);
        dto.setSlug(this.userNicename);
        dto.setAvatarUrls(Map.of(
            "24", "http://2.gravatar.com/avatar/" + this.userEmail.hashCode() + "?s=24&d=mm&r=g",
            "48", "http://2.gravatar.com/avatar/" + this.userEmail.hashCode() + "?s=48&d=mm&r=g",
            "96", "http://2.gravatar.com/avatar/" + this.userEmail.hashCode() + "?s=96&d=mm&r=g"
        ));
        dto.setMeta(new ArrayList<>());
        return dto;
    }
}