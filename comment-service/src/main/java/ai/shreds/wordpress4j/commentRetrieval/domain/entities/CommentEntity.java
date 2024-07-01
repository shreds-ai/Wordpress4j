package ai.shreds.wordpress4j.commentRetrieval.domain.entities;

import ai.shreds.wordpress4j.commentRetrieval.adapters.secondary.GravatarAdapter;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name = "wp_comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_ID")
    @NotNull
    private Long comment_ID;

    @Column(name = "comment_post_ID")
    @NotNull
    private Long comment_post_ID;

    @Column(name = "comment_parent")
    private Long comment_parent;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "comment_content", length = 500)
    @NotNull
    private String comment_content;

    @Column(name = "comment_approved")
    private String comment_approved;

    @Column(name = "comment_author")
    private String comment_author;

    @Column(name = "comment_author_email")
    private String comment_author_email;

    @Column(name = "comment_author_url")
    private String comment_author_url;

    @Column(name = "comment_author_IP")
    private String comment_author_IP;

    @Column(name = "comment_date")
    private LocalDateTime comment_date;

    @Column(name = "comment_date_gmt")
    private LocalDateTime comment_date_gmt;

    @Column(name = "comment_karma")
    private Integer comment_karma;

    @Column(name = "comment_agent")
    private String comment_agent;

    @Column(name = "comment_type")
    private String comment_type;

    @OneToMany
    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id")
    private Set<CommentMetaEntity> comment_meta;

    public Map<String, String> getAuthorAvatarUrls() {
        try {
            GravatarAdapter gravatarAdapter = new GravatarAdapter();
            return gravatarAdapter.fetchAvatarUrls(this.comment_author_email);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "CommentEntity{comment_ID=" + comment_ID +
               ", comment_post_ID=" + comment_post_ID +
               ", comment_parent=" + comment_parent +
               ", user_id=" + user_id +
               ", comment_content='" + comment_content + "'" +
               ", comment_approved=" + comment_approved + "}";
    }
}