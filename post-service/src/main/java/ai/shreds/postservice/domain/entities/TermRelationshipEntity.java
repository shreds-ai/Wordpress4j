package ai.shreds.postservice.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "wp_term_relationships", schema = "wordpress")
public class TermRelationshipEntity {
    @EmbeddedId
    private TermRelationshipIdEntity id;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "term_order", nullable = false)
    private Integer termOrder;

}