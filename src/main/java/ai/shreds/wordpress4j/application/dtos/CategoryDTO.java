package ai.shreds.wordpress4j.application.dtos;

import ai.shreds.wordpress4j.domain.entities.CategoryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("description")
    private String description;
    @JsonProperty("parentId")
    private Long parentId;
    @JsonProperty("postCount")
    private Integer postCount;

    public static CategoryDTO toCategoryDTO(CategoryEntity categoryEntity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(categoryEntity.getId());
        dto.setName(categoryEntity.getName());
        dto.setSlug(categoryEntity.getSlug());
        dto.setDescription(categoryEntity.getDescription());
        dto.setParentId(categoryEntity.getParentId());
        dto.setPostCount(categoryEntity.getPostCount());
        return dto;
    }

    public static CategoryEntity fromCategoryDTO(CategoryDTO categoryDTO) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(categoryDTO.getId());
        entity.setName(categoryDTO.getName());
        entity.setSlug(categoryDTO.getSlug());
        entity.setDescription(categoryDTO.getDescription());
        entity.setParentId(categoryDTO.getParentId());
        entity.setPostCount(categoryDTO.getPostCount());
        return entity;
    }
}