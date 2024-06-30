package com.example.wordpressclone.application.services;

import com.example.wordpressclone.application.dtos.CategoryDTO;
import com.example.wordpressclone.domain.entities.CategoryEntity;
import com.example.wordpressclone.domain.ports.CategoryRepositoryPort;
import com.example.wordpressclone.application.ports.CategoryOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryApplicationService.class);

    private final CategoryRepositoryPort categoryRepositoryPort;
    private final CategoryOutputPort categoryOutputPort;

    public CategoryApplicationService(CategoryRepositoryPort categoryRepositoryPort, CategoryOutputPort categoryOutputPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
        this.categoryOutputPort = categoryOutputPort;
    }

    public List<CategoryDTO> retrieveAllCategories() {
        logger.info("Retrieving all categories");
        List<CategoryEntity> categories = categoryRepositoryPort.findAllCategories();
        return categories.stream()
                .map(entity -> new CategoryDTO(entity.getId(), entity.getName(), entity.getSlug(), entity.getDescription(), entity.getParentId(), entity.getCount()))
                .collect(Collectors.toList());
    }

    public CategoryDTO retrieveCategoryById(Long id) {
        logger.info("Retrieving category by ID: {}", id);
        Optional<CategoryEntity> category = categoryRepositoryPort.findCategoryById(id);
        return category.map(entity -> new CategoryDTO(entity.getId(), entity.getName(), entity.getSlug(), entity.getDescription(), entity.getParentId(), entity.getCount()))
                .orElseThrow(() -> new CategoryNotFoundException("Category not found for ID: " + id));
    }

    public CategoryDTO retrieveCategoryByName(String name) {
        logger.info("Retrieving category by name: {}", name);
        Optional<CategoryEntity> category = categoryRepositoryPort.findCategoryByName(name);
        return category.map(entity -> new CategoryDTO(entity.getId(), entity.getName(), entity.getSlug(), entity.getDescription(), entity.getParentId(), entity.getCount()))
                .orElseThrow(() -> new CategoryNotFoundException("Category not found for name: " + name));
    }
}