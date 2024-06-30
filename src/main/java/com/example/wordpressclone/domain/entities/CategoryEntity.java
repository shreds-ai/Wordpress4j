package com.example.wordpressclone.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wp_terms")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "slug", nullable = false)
    @JsonProperty("slug")
    private String slug;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "parent")
    @JsonProperty("parentId")
    private Long parentId;

    @Column(name = "count")
    @JsonProperty("postCount")
    private int postCount;
}