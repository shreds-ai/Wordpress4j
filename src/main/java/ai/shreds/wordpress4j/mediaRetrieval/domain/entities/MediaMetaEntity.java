package ai.shreds.wordpress4j.mediaRetrieval.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wp_postmeta")
public class MediaMetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_id")
    private Long metaId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "meta_key", nullable = false)
    private String metaKey;

    @Column(name = "meta_value")
    private String metaValue;

}
