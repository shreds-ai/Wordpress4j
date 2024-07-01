package ai.shreds.wordpress4j.commentRetrieval.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wp_commentmeta")
public class CommentMetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_id", nullable = false)
    private Long metaId;

    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @Column(name = "meta_key")
    private String metaKey;

    @Column(name = "meta_value")
    private String metaValue;

}
