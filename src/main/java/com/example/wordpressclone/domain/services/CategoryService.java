package com.example.wordpressclone.domain.services;

import com.example.wordpressclone.domain.entities.Category;
import com.example.wordpressclone.domain.exceptions.CategoryNotFoundException;
import com.example.wordpressclone.domain.ports.CategoryRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<Category> retrieveAllCategories() {
        return categoryRepositoryPort.findAllCategories()
                .stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    public Category retrieveCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepositoryPort.findCategoryById(categoryId)
                .map(this::convertToDomain);
        return category.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Transactional
    public List<Category> retrieveCategoryByName(String categoryName) {
        return categoryRepositoryPort.findCategoryByName(categoryName)
                .stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    private Category convertToDomain(Category categoryEntity) {
        Category category = new Category();
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