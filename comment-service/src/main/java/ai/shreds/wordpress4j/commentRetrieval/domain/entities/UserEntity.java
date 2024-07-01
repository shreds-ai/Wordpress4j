package ai.shreds.wordpress4j.commentRetrieval.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import lombok.Data;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

/**
 * Represents a user entity correlating to the wp_users table in the WordPress database.
 * This entity maps directly to the database table and includes fields for user ID and email,
 * which are essential for generating Gravatar URLs.
 */
@Data
@Entity
@Table(name = "wp_users")
public class UserEntity {

    @Id
    @NotNull
    private Long id;

    @NotNull
    @Email
    @Column(name = "user_email")
    private String email;

    public UserEntity() {}

    public UserEntity(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserEntity that = (UserEntity) obj;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    /**
     * Generates a Gravatar URL for the user based on the email hash and the specified size.
     *
     * @param size The size of the avatar image.
     * @return The URL of the Gravatar image.
     */
    public String generateGravatarUrl(int size) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(email.getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return String.format("https://www.gravatar.com/avatar/%s?s=%d&d=identicon", hashText.toString(), size);
        } catch (Exception e) {
            throw new RuntimeException("Error generating Gravatar URL", e);
        }
    }
}