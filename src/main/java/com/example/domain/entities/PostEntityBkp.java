package com.example.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wp_posts")
@Data
@NoArgsConstructor
public class PostEntityBkp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_date")
    private String postDate;

    @Column(name = "post_date_gmt")
    private String postDateGmt;

    @Column(name = "post_modified")
    private String postModified;

    @Column(name = "post_modified_gmt")
    private String postModifiedGmt;

    @Column(name = "post_name")
    private String slug;

    @Column(name = "post_status")
    private String postStatus;

    @Column(name = "post_type")
    private String postType;

    @Column(name = "guid")
    private String guid;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_excerpt")
    private String postExcerpt;

    @Column(name = "post_author")
    private Long postAuthor;

    @Column(name = "comment_status")
    private String commentStatus;

    @Column(name = "ping_status")
    private String pingStatus;

    @Transient
    private List<String> categories;

    @Transient
    private List<String> tags;

    @Transient
    private String authorName;

    @Transient
    private String link;

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

    @Size(max = 255)
    @NotNull
    @Column(name = "post_password", nullable = false)
    private String postPassword;

    @NotNull
    @Lob
    @Column(name = "to_ping", nullable = false)
    private String toPing;

    @NotNull
    @Lob
    @Column(name = "pinged", nullable = false)
    private String pinged;

    @NotNull
    @Lob
    @Column(name = "post_content_filtered", nullable = false)
    private String postContentFiltered;

    @NotNull
    @Column(name = "post_parent", nullable = false)
    private Long postParent;

    @NotNull
    @Column(name = "menu_order", nullable = false)
    private Integer menuOrder;

    @Size(max = 100)
    @NotNull
    @Column(name = "post_mime_type", nullable = false, length = 100)
    private String postMimeType;

    @NotNull
    @Column(name = "comment_count", nullable = false)
    private Long commentCount;

}
