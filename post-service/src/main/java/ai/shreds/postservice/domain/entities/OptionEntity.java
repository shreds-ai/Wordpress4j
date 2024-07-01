package ai.shreds.postservice.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "wp_options", schema = "wordpress")
public class OptionEntity {
    @Id
    @Column(name = "option_id", nullable = false)
    private Long id;

    @Size(max = 191)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "option_name", nullable = false, length = 191)
    private String optionName;

    @NotNull
    @Lob
    @Column(name = "option_value", nullable = false)
    private String optionValue;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'yes'")
    @Column(name = "autoload", nullable = false, length = 20)
    private String autoload;

}