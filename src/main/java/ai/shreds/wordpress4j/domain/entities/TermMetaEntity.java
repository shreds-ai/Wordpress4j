package ai.shreds.wordpress4j.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wp_termmeta")
public class TermMetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_id", nullable = false)
    @JsonProperty("metaId")
    private Long metaId;

    @Column(name = "term_id", nullable = false)
    @JsonProperty("termId")
    private Long termId;

    @Column(name = "meta_key")
    @JsonProperty("metaKey")
    private String metaKey;

    @Column(name = "meta_value")
    @JsonProperty("metaValue")
    private String metaValue;

}
