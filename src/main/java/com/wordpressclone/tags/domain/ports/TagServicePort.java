package com.wordpressclone.tags.domain.ports;

import com.wordpressclone.tags.application.dtos.TagDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wordpressclone.tags.domain.exceptions.TagNotFoundException;

/**
 * Interface defining the operations for tag services within the domain layer.
 * Ensures decoupling and adherence to hexagonal architecture.
 */
public interface TagServicePort {

    /**
     * Retrieves a tag by its name from the repository.
     * Validates the input to ensure the name is not null or empty.
     * Throws TagNotFoundException if no tag is found.
     *
     * @param name the name of the tag to retrieve
     * @return TagDTO with tag details
     * @throws TagNotFoundException if the tag cannot be found
     * @throws IllegalArgumentException if name is null or empty
     */
    default TagDTO retrieveTagByName(String name) throws TagNotFoundException, IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be null or empty");
        }
        return retrieveTagByName(name);
    }

    /**
     * Validates the tag name to ensure it is not null or empty.
     * Throws IllegalArgumentException if the validation fails.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if name is null or empty
     */
    void validateTagName(String name) throws IllegalArgumentException;
}