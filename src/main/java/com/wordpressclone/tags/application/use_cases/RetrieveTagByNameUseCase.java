package com.wordpressclone.tags.application.use_cases;

import com.wordpressclone.tags.application.dtos.TagDTO;
import com.wordpressclone.tags.domain.entities.TagEntity;
import com.wordpressclone.tags.domain.exceptions.TagNotFoundException;
import com.wordpressclone.tags.domain.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Use case for retrieving a tag by its name from the domain layer.
 */
@Component
public class RetrieveTagByNameUseCase {

    private final TagService tagService;

    @Autowired
    public RetrieveTagByNameUseCase(TagService tagService) {
        this.tagService = tagService;
    }

    public TagDTO execute(String tagName) throws TagNotFoundException, IllegalArgumentException {
        if (tagName == null || tagName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name must not be null or empty");
        }
        try {
            TagEntity tagEntity = tagService.retrieveTagByName(tagName);
            return TagDTO.fromEntity(tagEntity);
        } catch (DataAccessException e) {
            throw new RuntimeException("Database access error occurred", e);
        }
    }
}