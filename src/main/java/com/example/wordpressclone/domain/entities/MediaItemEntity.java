package com.example.wordpressclone.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.io.Serializable;
import jakarta.json.bind.annotation.JsonbDateFormat;

/**
 * Represents a media item in the WordPress database.
 */
@Entity
@Table(name = "wp_posts")
public class MediaItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_author")
    private Long postAuthor;

    @Column(name = "post_date")
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime postDate;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_status")
    private String postStatus;

    @Column(name = "post_type")
    private String postType;

    @Column(name = "post_mime_type")
    private String postMimeType;

    @Column(name = "guid")
    private String guid;

    @Version
    private Integer version;

    /**
     * Lifecycle method to handle pre-persistence logic.
     */
    @PrePersist
    public void onPrePersist() {
        if (postDate == null) {
            postDate = LocalDateTime.now();
        }
    }

    /**
     * Lifecycle method to handle pre-update logic.
     */
    @PreUpdate
    public void onPreUpdate() {
        // This method can be used to set lastModified date if needed
    }

    // Getters and setters with null checks
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        this.id = id;
    }

    public Long getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Long postAuthor) {
        if (postAuthor == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }
        this.postAuthor = postAuthor;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        if (postDate == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.postDate = postDate;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        if (postTitle == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        this.postTitle = postTitle;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        if (postStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.postStatus = postStatus;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        if (postType == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        this.postType = postType;
    }

    public String getPostMimeType() {
        return postMimeType;
    }

    public void setPostMimeType(String postMimeType) {
        if (postMimeType == null) {
            throw new IllegalArgumentException("MIME Type cannot be null");
        }
        this.postMimeType = postMimeType;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        if (guid == null) {
            throw new IllegalArgumentException("GUID cannot be null");
        }
        this.guid = guid;
    }
}