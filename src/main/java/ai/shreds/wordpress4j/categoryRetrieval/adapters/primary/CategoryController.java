package ai.shreds.wordpress4j.categoryRetrieval.adapters.primary;

import ai.shreds.wordpress4j.categoryRetrieval.application.dtos.CategoryDTO;
import ai.shreds.wordpress4j.categoryRetrieval.application.services.CategoryApplicationService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryApplicationService categoryApplicationService;

    public CategoryController(CategoryApplicationService categoryApplicationService) {
        this.categoryApplicationService = categoryApplicationService;
    }

    @GetMapping("/categories/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        logger.info("Fetching all categories");
        return ResponseEntity.ok(categoryApplicationService.retrieveAllCategories());
    }

    @GetMapping("/categories/id/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        logger.info("Fetching category by ID: {}", id);
        return ResponseEntity.ok(categoryApplicationService.retrieveCategoryById(id));
    }

    @GetMapping("/categories/name")
    public ResponseEntity<CategoryDTO> getCategoryByName(@RequestParam String name) {
        logger.info("Fetching category by name: {}", name);
        return ResponseEntity.ok(categoryApplicationService.retrieveCategoryByName(name));
    }

}