package com.wordpressclone.tags.adapters.primary;

import com.wordpressclone.tags.application.dtos.TagDTO;
import com.wordpressclone.tags.application.ports.TagInputPort;
import com.wordpressclone.tags.domain.exceptions.TagNotFoundException;
import com.wordpressclone.tags.domain.exceptions.TagValidationException;
import com.wordpressclave.tags.domain.ports.TagRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

@Component
public class TagInputAdapter implements TagInputPort {

    private static final Logger logger = LoggerFactory.getLogger(TagInputAdapter.class);
    private final TagRepositoryPort repository;

    public TagInputAdapter(TagRepositoryPort repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void addTag(@Valid TagDTO tag) throws TagValidationException {
        try {
            repository.saveTag(tag);
            logger.info("Tag added successfully.");
        } catch (Exception e) {
            logger.error("Failed to add tag", e);
            throw new TagValidationException("Validation failed for tag: " + tag.getName(), e);
        }
    }

    @Transactional
    @Override
    public void updateTag(Long tagId, @Valid TagDTO tag) throws TagNotFoundException, TagValidationException {
        try {
            repository.updateTag(tagId, tag);
            logger.info("Tag updated successfully.");
        } catch (Exception e) {
            logger.error("Failed to update tag", e);
            throw new TagNotFoundException("Tag not found with ID: " + tagId, e);
        }
    }

    @Transactional
    @Override
    public void deleteTag(Long tagId) throws TagNotFoundException {
        try {
            repository.deleteTag(tagId);
            logger.info("Tag deleted successfully.");
        } catch (Exception e) {
            logger.error("Failed to delete tag", e);
            throw new TagNotFoundException("Tag not found with ID: " + tagId, e);
        }
    }
}