package ai.shreds.postservice.domain.entities;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "wp_posts", schema = "wordpress")
public class PostEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "post_date", nullable = false)
    private Instant postDate;

    @NotNull
    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "post_date_gmt", nullable = false)
    private Instant postDateGmt;

    @NotNull
    @Lob
    @Column(name = "post_content", nullable = false)
    private String postContent;

    @NotNull
    @Lob
    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @NotNull
    @Lob
    @Column(name = "post_excerpt", nullable = false)
    private String postExcerpt;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'publish'")
    @Column(name = "post_status", nullable = false, length = 20)
    private String postStatus;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'open'")
    @Column(name = "comment_status", nullable = false, length = 20)
    private String commentStatus;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'open'")
    @Column(name = "ping_status", nullable = false, length = 20)
    private String pingStatus;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "post_password", nullable = false)
    private String postPassword;

    @Size(max = 200)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "post_name", nullable = false, length = 200)
    private String postName;

    @NotNull
    @Lob
    @Column(name = "to_ping", nullable = false)
    private String toPing;

    @NotNull
    @Lob
    @Column(name = "pinged", nullable = false)
    private String pinged;

    @NotNull
    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "post_modified", nullable = false)
    private Instant postModified;

    @NotNull
    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "post_modified_gmt", nullable = false)
    private Instant postModifiedGmt;

    @NotNull
    @Lob
    @Column(name = "post_content_filtered", nullable = false)
    private String postContentFiltered;

    @NotNull
    @ColumnDefault("'0'")
    @Column(name = "post_parent", nullable = false)
    private Long postParent;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "guid", nullable = false)
    private String guid;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "menu_order", nullable = false)
    private Integer menuOrder;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'post'")
    @Column(name = "post_type", nullable = false, length = 20)
    private String postType;

    @Size(max = 100)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "post_mime_type", nullable = false, length = 100)
    private String postMimeType;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "comment_count", nullable = false)
    private Long commentCount;


    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments =  new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostmetaEntity> postMeta =  new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "wp_term_relationships",
            joinColumns = @JoinColumn(name = "object_id"),
            inverseJoinColumns = @JoinColumn(name = "term_taxonomy_id")
    )
    private List<TermTaxonomyEntity> termTaxonomies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "post_author")
    private UserEntity postAuthor;

    @Transient
    private List<TermEntity> categories = new ArrayList<>();

    @Transient
    private List<TermEntity> tags = new ArrayList<>();

    @Transient
    private String authorName;

    @Transient
    private String guidRendered;

    @Transient
    private String titleRendered;

    @Transient
    private String contentRendered;

    @Transient
    private String excerptRendered;

    @Transient
    private Long featuredMedia;

}