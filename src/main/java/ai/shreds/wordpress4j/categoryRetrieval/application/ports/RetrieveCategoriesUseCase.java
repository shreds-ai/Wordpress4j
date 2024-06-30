package ai.shreds.wordpress4j.categoryRetrieval.application.ports;

import ai.shreds.wordpress4j.categoryRetrieval.application.exceptions.DataAccessException;
import ai.shreds.wordpress4j.categoryRetrieval.application.exceptions.DataNotFoundException;
import ai.shreds.wordpress4j.categoryRetrieval.application.dtos.CategoryDTO;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Defines the input port for use cases related to retrieving categories.
 * This includes fetching all categories, a single category by ID, or by name.
 */
public interface RetrieveCategoriesUseCase {

    /**
     * Retrieves a list of all categories from the database.
     * Ensures data consistency and handles possible data retrieval failures.
     *
     * @return List of CategoryDTO representing all categories
     * @throws DataAccessException if data retrieval fails
     */
    List<CategoryDTO> fetchAllCategories() throws DataAccessException;

    /**
     * Retrieves a specific category by its unique identifier.
     * Includes error handling for non-existing IDs.
     *
     * @param id The unique identifier of the category
     * @return CategoryDTO representing the category found, or null if not found
     * @throws DataNotFoundException if the category is not found
     */
    CategoryDTO fetchCategoryById(@NotNull Long id) throws DataNotFoundException;

    /**
     * Retrieves a specific category by its name.
     * Handles errors such as category not found.
     *
     * @param name The name of the category
     * @return CategoryDTO representing the category found, or null if not found
     * @throws DataNotFoundException if the category is not found
     */
    CategoryDTO fetchCategoryByName(@NotNull String name) throws DataNotFoundException;
}