package com.wordpressclone.tags.application.use_cases;

import com.wordpressclone.tags.domain.services.TagService;
import com.wordpressclone.tags.domain.exceptions.TagNotFoundException;
import com.wordpressclone.tags.application.dtos.TagDTO;
import com.wordpressclone.tags.domain.entities.TagEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RetrieveTagByIdUseCase {

    private final TagService tagService;
    private static final Logger logger = LoggerFactory.getLogger(RetrieveTagByIdUseCase.class);

    public RetrieveTagByIdUseCase(TagService tagService) {
        this.tagService = tagService;
    }

    public TagDTO execute(Long tagId) throws TagNotFoundException, JsonProcessingException {
        validateTagId(tagId);
        logger.info("Executing tag retrieval for ID: {}", tagId);
        TagEntity tagEntity = tagService.retrieveTagById(tagId);
        TagDTO tag = TagDTO.fromJson(tagEntity.toJson());
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