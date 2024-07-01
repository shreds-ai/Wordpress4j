package ai.shreds.postservice.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "wp_term_taxonomy", schema = "wordpress")
public class TermTaxonomyEntity {
    @Id
    @Column(name = "term_taxonomy_id", nullable = false)
    private Long id;


    @Size(max = 32)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "taxonomy", nullable = false, length = 32)
    private String taxonomy;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private TermEntity term;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "count", nullable = false)
    private Long count;

    @ManyToOne
    @JoinColumn(name = "parent")
    private TermTaxonomyEntity parent;

    @ManyToMany(mappedBy = "termTaxonomies")
    private List<PostEntity> posts;

}