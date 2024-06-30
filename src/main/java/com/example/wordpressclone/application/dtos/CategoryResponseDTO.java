package com.example.wordpressclone.application.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import com.example.wordpressclone.application.dtos.CategoryDTO;
import com.example.wordpressclone.application.ports.CategoryDataTransformer;

@Data
@AllArgsConstructor
public class CategoryResponseDTO implements CategoryDataTransformer {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private Long parentId;
    private int postCount;

    @Override
    public CategoryResponseDTO transform(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("CategoryDTO cannot be null");
        }
        return new CategoryResponseDTO(
            categoryDTO.getId(),
            categoryDTO.getName(),
            categoryDTO.getSlug(),
            categoryDTO.getDescription(),
            categoryDTO.getParentId(),
            categoryDTO.getPostCount()
        );
    }

    @Override
    public String toString() {
        return "CategoryResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", parentId=" + parentId +
                ", postCount=" + postCount +
                '}';
    }
}