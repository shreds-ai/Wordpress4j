package ai.shreds.wordpress4j.commentRetrieval.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for User information.
 */
@Data
@NoArgsConstructor
public class UserDTO {

    /**
     * Unique identifier for the user, must be positive and not null.
     */
    @NotNull
    @Positive
    private Long id;

    /**
     * Email address of the user, validated against email format.
     */
    @Email
    @NotNull
    private String email;

    /**
     * Constructs a new UserDTO with specified id and email.
     *
     * @param id the user ID
     * @param email the user email
     */
    public UserDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}