package com.example.wordpressclone.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import lombok.Data;

/**
 * Represents the metadata associated with a user in the WordPress database.
 * Maps to the 'wp_usermeta' table.
 */
@Entity
@Table(name = "wp_usermeta")
@Data
public class UserMetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public UserMetaEntity() {
    }

    public UserMetaEntity(Long userId, String metaKey, String metaValue) {
        this.userId = userId;
        this.metaKey = metaKey;
        this.metaValue = metaValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMetaEntity that = (UserMetaEntity) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(metaKey, that.metaKey) &&
               Objects.equals(metaValue, that.metaValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, metaKey, metaValue);
    }

    @Override
    public String toString() {
        return "UserMetaEntity{" +
               "userId=" + userId +
               ", metaKey='" + metaKey + '\'' +
               ", metaValue='" + metaValue + '\'' +
               '}';
    }
}