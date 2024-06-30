package com.example.domain.value_objects;

import com.example.domain.ports.PostListParameters;
import com.example.domain.entities.PostEntity;
import javax.validation.ConstraintViolationException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the filter criteria for retrieving a list of posts, containing various filtering parameters,
 * ensuring data integrity and consistency within the domain model.
 */
public interface PostListFilter {

    Logger logger = LoggerFactory.getLogger(PostListFilter.class);

    /**
     * Applies the filtering criteria based on the given parameters and returns a list of filtered PostEntity objects.
     *
     * @param parameters The filtering parameters to apply.
     * @return A list of filtered PostEntity objects.
     */
    List<PostEntity> applyFilters(PostListParameters parameters);

    /**
     * Validates the filtering parameters using Hibernate Validator to ensure they meet the predefined constraints.
     *
     * @param parameters The filtering parameters to validate.
     * @throws ConstraintViolationException if any of the parameters fail the validation.
     */
    void validateParameters(PostListParameters parameters);

    /**
     * Handles exceptions related to invalid filter parameters and database interactions.
     */
    default void handleExceptions(Exception e) {
        if (e instanceof ConstraintViolationException) {
            logger.error("Validation error: ", e);
        } else {
            logger.error("Unexpected error: ", e);
        }
    }

    /**
     * Logs detailed debug information during the filtering process to aid in troubleshooting and maintenance.
     */
    default void logDebugInfo(PostListParameters parameters, List<PostEntity> results) {
        logger.debug("Filtering with parameters: {}", parameters);
        logger.debug("Results found: {}", results.size());
    }
}