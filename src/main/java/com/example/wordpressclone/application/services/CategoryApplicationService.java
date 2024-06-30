package com.example.wordpressclone.application.services;

import com.example.wordpressclone.application.dtos.CategoryDTO;
import com.example.wordpressclone.domain.entities.CategoryEntity;
import com.example.wordpressclone.domain.ports.CategoryRepositoryPort;
import com.example.wordpressclone.application.ports.CategoryOutputPort;
import com.example.wordpressclone.domain.exceptions.CategoryNotFoundException;
import com.example.wordpressclone.application.exceptions.DataAccessException;
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

    public List<CategoryDTO> retrieveAllCategories() throws DataAccessException {
        try {
            logger.info("Retrieving all categories");
            List<CategoryEntity> categories = categoryRepositoryPort.findAllCategories();
            return categories.stream()
                    .map(CategoryDTO::toCategoryDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataAccessException("Failed to retrieve all categories", e);
        }
    }

    public CategoryDTO retrieveCategoryById(Long id) throws CategoryNotFoundException, DataAccessException {
        try {
            logger.info("Retrieving category by ID: {}", id);
            Optional<CategoryEntity> category = categoryRepositoryPort.findCategoryById(id);
            return category.map(CategoryDTO::toCategoryDTO)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found for ID: " + id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to retrieve category by ID", e);
        }
    }

    public CategoryDTO retrieveCategoryByName(String name) throws CategoryNotFoundException, DataAccessException {
        try {
            logger.info("Retrieving category by name: {}", name);
            Optional<CategoryEntity> category = categoryRepositoryPort.findCategoryByName(name);
            return category.map(CategoryDTO::toCategoryDTO)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found for name: " + name));
        } catch (Exception e) {
            throw new DataAccessException("Failed to retrieve category by name", e);
        }
    }
}