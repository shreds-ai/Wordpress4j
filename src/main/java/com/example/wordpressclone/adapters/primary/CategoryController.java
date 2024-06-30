package com.example.wordpressclone.adapters.primary;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.wordpressclone.application.services.CategoryApplicationService;
import com.example.wordpressclone.application.dtos.CategoryDTO;
import com.example.wordpressclone.domain.exceptions.CategoryNotFoundException;
import com.example.wordpressclone.adapters.exceptions.ErrorResponse;

@RestController
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryApplicationService categoryApplicationService;

    public CategoryController(CategoryApplicationService categoryApplicationService) {
        this.categoryApplicationService = categoryApplicationService;
    }

    @GetMapping("/categories/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        try {
            logger.info("Fetching all categories");
            return ResponseEntity.ok(categoryApplicationService.retrieveAllCategories());
        } catch (DataAccessException e) {
            logger.error("Database access error", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/categories/id/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        try {
            logger.info("Fetching category by ID: {}", id);
            return ResponseEntity.ok(categoryApplicationManager.retrieveCategoryById(id));
        } catch (CategoryNotFoundException e) {
            logger.error("Category not found: {}", id, e);
            return ResponseEntity.status(404).body(new ErrorResponse("Category not found", "404"));
        } catch (DataAccessException e) {
            logger.error("Database access error", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/categories/name")
    public ResponseEntity<CategoryDTO> getCategoryByName(@RequestParam String name) {
        try {
            logger.info("Fetching category by name: {}", name);
            return ResponseEntity.ok(categoryApplicationService.retrieveCategoryByName(name));
        } catch (CategoryNotFoundException e) {
            logger.error("Category not found: {}", name, e);
            return ResponseEntity.status(404).body(new ErrorResponse("Category not found", "404"));
        } catch (DataAccessException e) {
            logger.error("Database access error", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @ExceptionHandler({CategoryNotFoundException.class, DataAccessException.class})
    public ResponseEntity<Object> handleExceptions(Exception ex) {
        if (ex instanceof CategoryNotFoundException) {
            logger.error("Handling category not found exception", ex);
            return ResponseEntity.status(404).body(new ErrorResponse("Category not found", "404"));
        } else if (ex instanceof DataAccessException) {
            logger.error("Database access error", ex);
            return ResponseEntity.status(500).body("Database access issue");
        }
        return ResponseEntity.status(500).body("Unexpected error");
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        try {
            logger.info("Creating new category with name: {}", categoryDTO.getName());
            return ResponseEntity.ok(categoryApplicationService.createCategory(categoryDTO));
        } catch (DataAccessException e) {
            logger.error("Database access error while creating category", e);
            return ResponseEntity.status(500).body(null);
        }
    }
}