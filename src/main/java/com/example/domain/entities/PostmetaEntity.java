package com.example.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "wp_postmeta", schema = "wordpress")
public class PostmetaEntity {
    @Id
    @Column(name = "meta_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "meta_key")
    private String metaKey;

    @Lob
    @Column(name = "meta_value")
    private String metaValue;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;


}