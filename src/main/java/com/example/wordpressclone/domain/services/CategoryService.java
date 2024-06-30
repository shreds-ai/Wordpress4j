package com.example.wordpressclone.domain.services;

import com.example.wordpressclone.domain.entities.CategoryEntity;
import com.example.wordpressclone.domain.exceptions.CategoryNotFoundException;
import com.example.wordpressclone.domain.ports.CategoryRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepositoryPort categoryRepositoryPort;

    public CategoryService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Transactional
    public List<CategoryEntity> retrieveAllCategories() {
        return categoryRepositoryPort.findAllCategories()
                .stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryEntity retrieveCategoryById(Long categoryId) throws CategoryNotFoundException {
        Optional<CategoryEntity> category = categoryRepositoryPort.findCategoryById(categoryId)
                .map(this::convertToDomain);
        return category.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Transactional
    public List<CategoryEntity> retrieveCategoryByName(String categoryName) throws CategoryNotFoundException {
        return categoryRepositoryPort.findCategoryByName(categoryName)
                .stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    private CategoryEntity convertToDomain(CategoryEntity categoryEntity) {
        CategoryEntity category = new CategoryEntity();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        category.setSlug(categoryEntity.getSlug());
        category.setDescription(categoryEntity.getDescription());
        category.setParent(categoryEntity.getParent());
        category.setCount(categoryEntity.getCount());
        return category;
    }

    private void handleDataAccessException(Exception e) {
        logger.error("Data access error: ", e);
    }
}