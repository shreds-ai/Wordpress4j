package com.wordpressclone.tags.adapters.primary;

import com.wordpressclone.tags.application.dtos.TagDTO;
import com.wordpressclone.tags.application.ports.TagInputPort;
import com.wordpressclone.tags.exceptions.TagNotFoundException;
import com.wordpressclone.tags.exceptions.TagValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Component
public class TagInputAdapter implements TagInputPort {

    private static final Logger logger = LoggerFactory.getLogger(TagInputAdapter.class);

    @Transactional
    @Override
    public void addTag(@Valid TagDTO tag) throws TagValidationException {
        try {
            // repository.add(tag);
            logger.info("Tag added successfully.");
        } catch (DataAccessException e) {
            logger.error("Error accessing data", e);
            throw new TagValidationException("Failed to add tag", e);
        }
    }

    @Transactional
    @Override
    public void updateTag(Long tagId, @Valid TagDTO tag) throws TagNotFoundException, TagValidationException {
        try {
            // repository.update(tagId, tag);
            logger.info("Tag updated successfully.");
        } catch (DataAccessException e) {
            logger.error("Error accessing data", e);
            throw new TagValidationException("Failed to update tag", e);
        }
    }

    @Transactional
    @Override
    public void deleteTag(Long tagId) throws TagNotFoundException {
        try {
            // repository.delete(tagId);
            logger.info("Tag deleted successfully.");
        } catch (DataAccessException e) {
            logger.error("Error accessing data", e);
            throw new TagNotFoundException("Failed to delete tag", e);
        }
    }

    // Additional methods or logic as required
}