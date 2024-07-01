package ai.shreds.wordpress4j.categoryRetrieval.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wp_term_taxonomy")
public class TermTaxonomyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_taxonomy_id", nullable = false)
    @JsonProperty("termTaxonomyId")
    private Long termTaxonomyId;

    @Column(name = "term_id", nullable = false)
    @JsonProperty("termId")
    private Long termId;

    @Column(name = "taxonomy", nullable = false)
    @JsonProperty("taxonomy")
    private String taxonomy;

    @Column(name = "description")
    @JsonProperty("taxonomyDescription")
    private String description;

    @Column(name = "parent")
    @JsonProperty("taxonomyParentId")
    private Long parentId;

    @Column(name = "count")
    @JsonProperty("taxonomyCount")
    private int count;

}
