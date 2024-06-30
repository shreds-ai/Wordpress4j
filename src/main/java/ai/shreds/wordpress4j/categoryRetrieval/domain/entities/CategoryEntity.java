package ai.shreds.wordpress4j.categoryRetrieval.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @Column(name = "term_group")
    @JsonProperty("term_group")
    private String termGroup;

    @OneToMany
    @JoinColumn(name = "term_id", referencedColumnName = "term_id")
    @JsonProperty("termTaxonomy")
    private Set<TermTaxonomyEntity> termTaxonomy;

    @OneToMany
    @JoinColumn(name = "term_id", referencedColumnName = "term_id")
    @JsonProperty("termMeta")
    private Set<TermMetaEntity> termMeta;

}