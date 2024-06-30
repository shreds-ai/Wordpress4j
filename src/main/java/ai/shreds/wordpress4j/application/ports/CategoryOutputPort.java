package ai.shreds.wordpress4j.application.ports;

import ai.shreds.wordpress4j.application.dtos.CategoryDTO;
import java.util.List;

/**
 * Defines the output interface for category retrieval operations.
 */
public interface CategoryOutputPort {

    /**
     * Outputs the details of a single category using the CategoryDTO.
     * This method is typically called by the application service layer to present data to the UI or external APIs.
     * Includes error handling for cases where category data is unavailable or invalid.
     *
     * @param categoryDTO the category data transfer object
     */
    void outputCategoryDetails(CategoryDTO categoryDTO);

    /**
     * Outputs a list of category details using a list of CategoryDTOs.
     * This method supports bulk data presentation, suitable for displaying multiple categories in UI components or sending bulk data in API responses.
     * Includes error handling for cases where category data is unavailable or invalid.
     *
     * @param categoriesDTO list of category data transfer objects
     */
    void outputCategoriesList(List<CategoryDTO> categoriesDTO);

    /**
     * Handles errors during category data retrieval.
     * @param exception the exception that occurred during category retrieval
     */
    void handleError(Exception exception);
}