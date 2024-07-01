package ai.shreds.wordpress4j.categoryRetrieval.application.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private Long parentId;
    private int postCount;

    public static CategoryResponseDTO convertFromCategoryDTO(CategoryDTO categoryDTO) {
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