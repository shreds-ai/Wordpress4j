package ai.shreds.wordpress4j.categoryRetrieval.application.dtos;

import ai.shreds.wordpress4j.categoryRetrieval.domain.entities.TermTaxonomyEntity;
import ai.shreds.wordpress4j.categoryRetrieval.domain.entities.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("count")
    private Integer postCount;
    @JsonProperty("description")
    private String description;
    @JsonProperty("link")
    private String link;
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("taxonomy")
    private String taxonomy;
    @JsonProperty("parent")
    private Long parentId;
    @JsonProperty("meta")
    private List<String> meta;

    public static CategoryDTO toCategoryDTO(CategoryEntity categoryEntity, String siteurl) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(categoryEntity.getId());
        dto.setName(categoryEntity.getName());
        dto.setSlug(categoryEntity.getSlug());
        dto.setDescription(categoryEntity.getTermTaxonomy().stream().findFirst().orElse(new TermTaxonomyEntity()).getDescription());
        dto.setParentId(categoryEntity.getTermTaxonomy().stream().findFirst().orElse(new TermTaxonomyEntity()).getParentId());
        dto.setPostCount(categoryEntity.getTermTaxonomy().stream().findFirst().orElse(new TermTaxonomyEntity()).getCount());
        dto.setTaxonomy(categoryEntity.getTermTaxonomy().stream().findFirst().orElse(new TermTaxonomyEntity()).getTaxonomy());
        dto.setLink(siteurl + "/categories/" + categoryEntity.getName());
        dto.setMeta(categoryEntity.getTermMeta().stream().map(meta -> meta.getMetaKey() + ": " + meta.getMetaValue()).toList());
        return dto;
    }

    public static CategoryEntity fromCategoryDTO(CategoryDTO categoryDTO) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(categoryDTO.getId());
        entity.setName(categoryDTO.getName());
        entity.setSlug(categoryDTO.getSlug());
//        entity.setDescription(categoryDTO.getDescription());
//        entity.setParentId(categoryDTO.getParentId());
//        entity.setPostCount(categoryDTO.getPostCount());
        return entity;
    }
}