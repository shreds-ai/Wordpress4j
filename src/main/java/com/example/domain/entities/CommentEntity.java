package com.example.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "wp_comments", schema = "wordpress")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_ID", nullable = false)
    private Long id;

    @NotNull
    @Lob
    @Column(name = "comment_author", nullable = false)
    private String commentAuthor;

    @Size(max = 100)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "comment_author_email", nullable = false, length = 100)
    private String commentAuthorEmail;

    @Size(max = 200)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "comment_author_url", nullable = false, length = 200)
    private String commentAuthorUrl;

    @Size(max = 100)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "comment_author_IP", nullable = false, length = 100)
    private String commentAuthorIp;

    @NotNull
    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "comment_date", nullable = false)
    private Instant commentDate;

    @NotNull
    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "comment_date_gmt", nullable = false)
    private Instant commentDateGmt;

    @NotNull
    @Lob
    @Column(name = "comment_content", nullable = false)
    private String commentContent;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "comment_karma", nullable = false)
    private Integer commentKarma;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'1'")
    @Column(name = "comment_approved", nullable = false, length = 20)
    private String commentApproved;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "comment_agent", nullable = false)
    private String commentAgent;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'comment'")
    @Column(name = "comment_type", nullable = false, length = 20)
    private String commentType;

    @ManyToOne
    @JoinColumn(name = "comment_parent")
    private CommentEntity parentComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "comment")
    private List<CommentmetaEntity> commentMeta;

    @ManyToOne
    @JoinColumn(name = "comment_post_ID")
    private PostEntity post;
}