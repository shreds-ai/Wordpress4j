package ai.shreds.wordpress4j.domain.ports;

import ai.shreds.wordpress4j.domain.entities.TagEntity;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Optional;

/**
 * Interface defining the contract for tag data access.
 * It declares methods for fetching tags by ID and name, and for retrieving all tags.
 * This port is used to abstract the data layer from the domain layer.
 *
 * @author AI Assistant
 */
public interface TagRepositoryPort {

    /**
     * Retrieves a list of all tags from the data source.
     * @return List<TagEntity> List of all tags
     * @throws DataAccessException if there is a problem accessing the database
     */
    List<TagEntity> findAllTags() throws DataAccessException;

    /**
     * Finds a single tag by its ID.
     * @param id the ID of the tag to find
     * @return Optional<TagEntity> Container that may or may not contain the found tag
     * @throws DataAccessException if there is an issue accessing the database
     */
    Optional<TagEntity> findTagById(Long id) throws DataAccessException;

    /**
     * Finds a single tag by its name.
     * @param name the name of the tag to find
     * @return Optional<TagEntity> Container that may or may not contain the found tag
     * @throws DataAccessException when database access errors occur
     */
    Optional<TagEntity> findTagByName(String name) throws DataAccessException;
}