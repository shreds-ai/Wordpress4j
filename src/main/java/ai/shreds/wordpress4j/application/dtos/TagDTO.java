package ai.shreds.wordpress4j.application.dtos;

import ai.shreds.wordpress4j.domain.entities.TagEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TagDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("description")
    private String description;

    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("taxonomy")
    private String taxonomy;

    @JsonProperty("meta")
    private List<String> meta;

    public static TagDTO fromEntity(TagEntity tagEntity) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tagEntity.getId());
        tagDTO.setName(tagEntity.getName());
        tagDTO.setSlug(tagEntity.getSlug());
        tagDTO.setDescription(tagEntity.getTermTaxonomy().stream().findFirst().get().getDescription());
        tagDTO.setCount(tagEntity.getTermTaxonomy().stream().findFirst().get().getCount());
        tagDTO.setTaxonomy(tagEntity.getTermTaxonomy().stream().findFirst().get().getTaxonomy());
        tagDTO.setMeta(tagEntity.getTermMeta().stream().map(meta -> meta.getMetaKey() + ": " + meta.getMetaValue()).toList());
        return tagDTO;
    }
}