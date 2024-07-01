package com.example.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "wp_links", schema = "wordpress")
public class LinkEntity {
    @Id
    @Column(name = "link_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "link_url", nullable = false)
    private String linkUrl;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "link_name", nullable = false)
    private String linkName;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "link_image", nullable = false)
    private String linkImage;

    @Size(max = 25)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "link_target", nullable = false, length = 25)
    private String linkTarget;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "link_description", nullable = false)
    private String linkDescription;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'Y'")
    @Column(name = "link_visible", nullable = false, length = 20)
    private String linkVisible;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "link_rating", nullable = false)
    private Integer linkRating;

    @NotNull
    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "link_updated", nullable = false)
    private Instant linkUpdated;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "link_rel", nullable = false)
    private String linkRel;

    @NotNull
    @Lob
    @Column(name = "link_notes", nullable = false)
    private String linkNotes;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "link_rss", nullable = false)
    private String linkRss;


    @ManyToOne
    @JoinColumn(name = "link_owner")
    private UserEntity owner;


}