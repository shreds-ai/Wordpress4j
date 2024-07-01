package ai.shreds.postservice.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Table(name = "wp_commentmeta", schema = "wordpress")
public class CommentmetaEntity {
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
    @JoinColumn(name = "comment_id")
    private CommentEntity comment;

}