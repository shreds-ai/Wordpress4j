package com.wordpressclone.tags.domain.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordpressclone.tags.domain.ports.TagRepositoryPort;

@Data
@Entity
@Table(name = "wp_terms")
public class TagEntity {

    @Id
    @Column(name = "term_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "count")
    private int postCount;

    private final TagRepositoryPort tagRepositoryPort;

    @Autowired
    public TagEntity(TagRepositoryPort tagRepositoryPort) {
        this.tagRepositoryPort = tagRepositoryPort;
    }

    public boolean validateTagDetails() {
        return name != null && !name.trim().isEmpty() && slug != null && !slug.trim().isEmpty();
    }

    public TagEntity findTagById(Long id) {
        Optional<TagEntity> tag = tagRepositoryPort.findTagById(id);
        return tag.orElse(null);
    }

    public boolean updateTag(TagEntity tag) {
        if (validateTagDetails() && checkPostCountValidity()) {
            tagRepositoryPort.save(tag);
            return true;
        }
        return false;
    }

    private boolean checkPostCountValidity() {
        return postCount >= 0;
    }
}