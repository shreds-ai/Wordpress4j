package ai.shreds.wordpress4j.domain.ports;

import ai.shreds.wordpress4j.domain.entities.CategoryEntity;
import java.util.List;
import java.util.Optional;

/**
 * Interface defining the contract for category data access.
 * Methods include fetching categories by ID, name, or retrieving all categories.
 */
public interface CategoryRepositoryPort {

    /**
     * Retrieve all categories from the database.
     * This method fetches and returns a list of all categories present in the WordPress database.
     *
     * @return a list of all category entities
     */
    List<CategoryEntity> findAllCategories();

    /**
     * Fetch a single category by its ID.
     * This method fetches details for a specific category based on its ID.
     *
     * @param id the ID of the category
     * @return an Optional containing the category entity if found
     */
    Optional<CategoryEntity> findCategoryById(Long id);

    /**
     * Fetch a single category by its name.
     * This method fetches details for a specific category based on its name.
     *
     * @param name the name of the category
     * @return an Optional containing the category entity if found
     */
    Optional<CategoryEntity> findCategoryByName(String name);
}