package ai.shreds.wordpress4j.domain.ports;

import ai.shreds.wordpress4j.domain.entities.TagEntity;
import ai.shreds.wordpress4j.domain.exceptions.TagNotFoundException;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interface defining the operations for tag services within the domain layer.
 * Ensures decoupling and adherence to hexagonal architecture.
 */
public interface TagServicePort {

    List<TagEntity> retrieveAllTags() throws DataAccessException;

    TagEntity retrieveTagById(Long id) throws DataAccessException, TagNotFoundException;

    /**
     * Retrieves a tag by its name from the repository.
     * Validates the input to ensure the name is not null or empty.
     * Throws TagNotFoundException if no tag is found.
     *
     * @param name the name of the tag to retrieve
     * @return TagDTO with tag details
     * @throws TagNotFoundException     if the tag cannot be found
     * @throws IllegalArgumentException if name is null or empty
     */
    TagEntity retrieveTagByName(String name) throws TagNotFoundException, IllegalArgumentException;

}