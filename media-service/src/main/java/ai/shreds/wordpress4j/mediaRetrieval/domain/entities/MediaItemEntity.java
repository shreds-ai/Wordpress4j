package ai.shreds.wordpress4j.mediaRetrieval.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a media item in the WordPress database.
 */
@Entity
@Getter
@Table(name = "wp_posts")
public class MediaItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_author")
    private Long postAuthor;

    @Column(name = "post_date")
    private LocalDateTime postDate;

    @Column(name = "post_date_gmt")
    private LocalDateTime postDateGmt;

    @Column(name = "post_modified")
    private LocalDateTime postModified;

    @Column(name = "post_modified_gmt")
    private LocalDateTime postModifiedGmt;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_status")
    private String postStatus;

    @Column(name = "comment_status")
    private String commentStatus;

    @Column(name = "ping_status")
    private String pingStatus;

    @Column(name = "post_type")
    private String postType;

    @Column(name = "post_mime_type")
    private String postMimeType;

    @Column(name = "guid")
    private String guid;

    @Column(name = "post_name")
    private String postName;

    @OneToMany
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @JsonProperty("postmeta")
    private Set<MediaMetaEntity> mediaMetaEntities;

}