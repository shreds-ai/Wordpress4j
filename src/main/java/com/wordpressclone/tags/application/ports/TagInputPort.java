package com.wordpressclone.tags.application.ports;

import com.wordpressclone.tags.application.dtos.TagDTO;
import com.wordpressclone.tags.domain.exceptions.TagNotFoundException;
import com.wordpressclone.tags.domain.exceptions.TagValidationException;

/**
 * Interface for input operations related to managing tags within the application.
 *
 * Provides methods to add, update, and delete tags based on business requirements.
 */
public interface TagInputPort {

    /**
     * Adds a new tag to the system based on the provided TagDTO object.
     * @param tag the tag DTO to add
     * @throws TagValidationException if the data is not valid
     */
    void addTag(TagDTO tag) throws TagValidationException;

    /**
     * Updates an existing tag identified by tagId with the new values provided in the TagDTO object.
     * @param tagId the ID of the tag to update
     * @param tag the tag DTO with updated values
     * @throws TagNotFoundException if the tag does not exist
     * @throws TagValidationException if the data is not valid
     */
    void updateTag(Long tagId, TagDTO tag) throws TagNotFoundException, TagValidationException;

    /**
     * Deletes an existing tag from the system identified by tagId.
     * @param tagId the ID of the tag to delete
     * @throws TagNotFoundException if the tag does not exist
     */
    void deleteTag(Long tagId) throws TagNotFoundException;
}