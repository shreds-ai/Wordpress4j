package ai.shreds.wordpress4j.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

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

    @OneToMany
    @JoinColumn(name = "term_id", referencedColumnName = "term_id")
    private Set<TermTaxonomyEntity> termTaxonomy;

    @OneToMany
    @JoinColumn(name = "term_id", referencedColumnName = "term_id")
    private Set<TermMetaEntity> termMeta;

}