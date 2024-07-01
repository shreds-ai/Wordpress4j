package ai.shreds.postservice.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TermRelationshipIdEntity implements java.io.Serializable {
    private static final long serialVersionUID = 4006323459848319089L;
    @NotNull
    @ColumnDefault("'0'")
    @Column(name = "object_id", nullable = false)
    private Long objectId;

    @NotNull
    @ColumnDefault("'0'")
    @Column(name = "term_taxonomy_id", nullable = false)
    private Long termTaxonomyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TermRelationshipIdEntity entity = (TermRelationshipIdEntity) o;
        return Objects.equals(this.termTaxonomyId, entity.termTaxonomyId) &&
                Objects.equals(this.objectId, entity.objectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termTaxonomyId, objectId);
    }

}