package com.wordpressclone.tags.application.use_cases;

import com.wordpressclone.tags.domain.services.TagService;
import com.wordpressclone.tags.domain.exceptions.TagNotFoundException;
import com.wordpressclone.tags.application.dtos.TagDTO;
import com.wordpressclone.tags.domain.entities.TagEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetrieveTagByIdUseCase {

    private final TagService tagService;
    private static final Logger logger = LoggerFactory.getLogger(RetrieveTagByIdUseCase.class);

    public RetrieveTagByIdUseCase(TagService tagService) {
        this.tagService = tagService;
    }

    public TagDTO execute(Long tagId) throws TagNotFoundException {
        validateTagId(tagId);
        logger.info("Executing tag retrieval for ID: {}", tagId);
        TagDTO tag = TagDTO.fromEntity(tagService.retrieveTagById(tagId));
        logger.info("Retrieved tag details: {}", tag);
        return tag;
    }

    private void validateTagId(Long tagId) {
        if (tagId == null || tagId < 1) {
            throw new IllegalArgumentException("Invalid Tag ID: " + tagId);
        }
        logger.debug("Validated tag ID: {}", tagId);
    }
}