package ai.shreds.wordpress4j.categoryRetrieval.application.services;

import ai.shreds.wordpress4j.categoryRetrieval.application.exceptions.DataAccessException;
import ai.shreds.wordpress4j.categoryRetrieval.application.ports.CategoryOutputPort;
import ai.shreds.wordpress4j.categoryRetrieval.domain.entities.OptionEntity;
import ai.shreds.wordpress4j.categoryRetrieval.domain.ports.CategoryRepositoryPort;
import ai.shreds.wordpress4j.categoryRetrieval.application.dtos.CategoryDTO;
import ai.shreds.wordpress4j.categoryRetrieval.domain.entities.CategoryEntity;
import ai.shreds.wordpress4j.categoryRetrieval.domain.exceptions.CategoryNotFoundException;
import ai.shreds.wordpress4j.categoryRetrieval.domain.ports.OptionRepositoryPort;
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
    private final OptionRepositoryPort optionRepositoryPort;

    public CategoryApplicationService(CategoryRepositoryPort categoryRepositoryPort, OptionRepositoryPort optionRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
        this.optionRepositoryPort = optionRepositoryPort;
    }

    public List<CategoryDTO> retrieveAllCategories() throws DataAccessException {
        try {
            logger.info("Retrieving all categories");
            OptionEntity siteurl = optionRepositoryPort.findByOptionName("siteurl");
            List<CategoryEntity> categories = categoryRepositoryPort.findAllCategories();
            return categories.stream()
                    .map(categoryEntity ->  CategoryDTO.toCategoryDTO(categoryEntity, siteurl.getOptionValue()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataAccessException("Failed to retrieve all categories", e);
        }
    }

    public CategoryDTO retrieveCategoryById(Long id) throws CategoryNotFoundException, DataAccessException {
        try {
            logger.info("Retrieving category by ID: {}", id);
            OptionEntity siteurl = optionRepositoryPort.findByOptionName("siteurl");
            Optional<CategoryEntity> category = categoryRepositoryPort.findCategoryById(id);
            return category.map(categoryEntity ->  CategoryDTO.toCategoryDTO(categoryEntity, siteurl.getOptionValue()))
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found for ID: " + id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to retrieve category by ID", e);
        }
    }

    public CategoryDTO retrieveCategoryByName(String name) throws CategoryNotFoundException, DataAccessException {
        try {
            logger.info("Retrieving category by name: {}", name);
            OptionEntity siteurl = optionRepositoryPort.findByOptionName("siteurl");
            Optional<CategoryEntity> category = categoryRepositoryPort.findCategoryByName(name);
            return category.map(categoryEntity ->  CategoryDTO.toCategoryDTO(categoryEntity, siteurl.getOptionValue()))
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found for name: " + name));
        } catch (Exception e) {
            throw new DataAccessException("Failed to retrieve category by name", e);
        }
    }
}