package com.example.wordpressclone.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Represents the metadata associated with a user in the WordPress database.
 * Maps to the 'wp_usermeta' table.
 */
@Entity
@Table(name = "wp_usermeta")
@Data
public class UserMetaEntity implements UserMetaEntityPort {

    @Id
    @Column(name = "umeta_id")
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Size(max = 255)
    @Column(name = "meta_key")
    private String metaKey;

    @Column(name = "meta_value")
    private String metaValue;

    /**
     * Default constructor for JPA.
     */
    public UserMetaEntity() {
    }

    /**
     * Constructs a new UserMetaEntity with specified user ID, meta key, and meta value.
     *
     * @param userId the ID of the user
     * @param metaKey the key for the metadata
     * @param metaValue the value of the metadata
     */
    public UserMetaEntity(Long userId, String metaKey, String metaValue) {
        this.userId = userId;
        this.metaKey = metaKey;
        this.metaValue = metaValue;
    }
}