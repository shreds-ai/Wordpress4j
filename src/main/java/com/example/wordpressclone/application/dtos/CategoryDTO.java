package com.example.wordpressclone.application.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.example.wordpressclone.application.ports.CategoryMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO implements CategoryMapper {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private Long parentId;
    private Integer postCount;

    @Override
    public static CategoryDTO toCategoryDTO(com.example.wordpressclone.domain.entities.CategoryEntity categoryEntity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(categoryEntity.getId());
        dto.setName(categoryEntity.getName());
        dto.setSlug(categoryEntity.getSlug());
        dto.setDescription(categoryEntity.getDescription());
        dto.setParentId(categoryEntity.getParentId());
        dto.setPostCount(categoryEntity.getPostCount());
        return dto;
    }

    @Override
    public static com.example.wordpressclone.domain.entities.CategoryEntity fromCategoryDTO(CategoryDTO categoryDTO) {
        return new com.example.wordpressclone.domain.entities.CategoryEntity(
            categoryDTO.getId(),
            categoryDTO.getName(),
            categoryDTO.getSlug(),
            categoryDTO.getDescription(),
            categoryDTO.getParentId(),
            categoryDTO.getPostCount()
        );
    }
}