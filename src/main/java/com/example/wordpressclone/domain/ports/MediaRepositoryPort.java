package com.example.wordpressclone.domain.ports;

import com.example.wordpressclone.domain.entities.MediaItemEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataAccessException;

/**
 * Interface for media repository operations required by the domain layer.
 * Defines the contract for fetching media items either by ID or all items.
 *
 * @author Project Team
 */
public interface MediaRepositoryPort {

    /**
     * Retrieve all media items from the database.
     * @return a List of MediaItemEntity representing all media items
     * @throws DataAccessException if there is an issue accessing the data
     */
    List<MediaItemEntity> findAllMediaItems() throws DataAccessException;

    /**
     * Find a single media item by its ID.
     * @param id the identifier of the media item
     * @return an Optional of MediaItemEntity if found, otherwise an empty Optional
     * @throws DataAccessException if there is a database access error
     */
    Optional<MediaItemEntity> findMediaItemById(Long id) throws DataAccessException;
}