package com.wordpressclone.tags.application.use_cases;

import com.wordpressclone.tags.domain.entities.TagDTO;
import com.wordpressclone.tags.domain.exceptions.TagNotFoundException;
import com.wordpressclone.tags.domain.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/tags")
    public TagDTO execute(@RequestParam String tagName) throws TagNotFoundException, IllegalArgumentException {
        if (tagName == null || tagName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name must not be null or empty");
        }
        try {
            return tagService.retrieveTagByName(tagName)
                    .map(TagDTO::fromEntity)
                    .orElseThrow(() -> new TagNotFoundException("Tag not found with name: " + tagName));
        } catch (DataAccessException e) {
            throw new RuntimeException("Database access error occurred", e);
        }
    }
}