package com.example.domain.ports;

import java.util.List;
import java.util.Optional;
import com.example.domain.entities.PostEntity;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;

/**
 * Defines the contract for retrieving and persisting posts, used by the application services
 * to interact with the data layer without being coupled to any specific database technology.
 */
public interface PostRepositoryPort {

    /**
     * Retrieves a post by its unique identifier from the database.
     * @param id The unique identifier of the post.
     * @return An Optional containing the post if found, or an empty Optional otherwise.
     */
    Optional<PostEntity> findById(Long id);

    /**
     * Retrieves a post by its slug, a URL-friendly version of the title.
     * @param slug The slug of the post.
     * @return An Optional containing the post if found, or an empty Optional otherwise.
     */
    Optional<PostEntity> findBySlug(String slug);

    /**
     * Retrieves all posts, with optional filtering parameters provided by PostListParameters.
     * @param listParameters The filtering parameters for post listing.
     * @return A list of PostEntity objects.
     */
    List<PostEntity> findAll(PostListParameters listParameters);

    /**
     * Persists a post to the data layer.
     * @param post The post to be saved.
     * @return The saved PostEntity object.
     */
    PostEntity save(PostEntity post);

    /**
     * Deletes a post by its unique identifier.
     * @param id The unique identifier of the post to be deleted.
     */
    void deleteById(Long id);

    /**
     * Handles SQL and data access exceptions to provide clearer error messages and improve
     * the robustness of database operations.
     * @param e The SQL exception to handle.
     * @param dae The data access exception to handle.
     * @return true if the exception is handled successfully, otherwise false.
     */
    boolean handleDatabaseException(SQLException e, DataAccessException dae);

    /**
     * Method to handle pagination details directly within the repository layer.
     * This method should encapsulate pagination logic, enhancing performance and maintainability.
     */
    void handlePaginationDetails();
}