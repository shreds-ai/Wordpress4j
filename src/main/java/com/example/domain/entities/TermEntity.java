package com.example.domain.entities;

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
@Table(name = "wp_terms", schema = "wordpress")
public class TermEntity {
    @Id
    @Column(name = "term_id", nullable = false)
    private Long id;

    @Size(max = 200)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Size(max = 200)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "slug", nullable = false, length = 200)
    private String slug;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "term_group", nullable = false)
    private Long termGroup;

    @OneToMany(mappedBy = "term")
    private List<TermTaxonomyEntity> termTaxonomies;

    @OneToMany(mappedBy = "term")
    private List<TermmetaEntity> termMeta;

}