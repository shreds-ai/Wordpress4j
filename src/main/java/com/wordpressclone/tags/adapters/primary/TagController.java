package com.wordpressclone.tags.adapters.primary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.wordpressclone.tags.application.use_cases.RetrieveTagsUseCase;
import com.wordpressclone.tags.application.use_cases.RetrieveTagByIdUseCase;
import com.wordpressclone.tags.application.use_cases.RetrieveTagByNameUseCase;
import com.wordpressclone.tags.application.dtos.TagDTO;
import com.wordpressclone.tags.domain.exceptions.TagNotFoundException;

@RestController
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);
    private final RetrieveTagsUseCase retrieveTagsUseCase;
    private final RetrieveTagByIdUseCase retrieveTagByIdUseCase;
    private final RetrieveTagByNameUseCase retrieveTagByNameUseCase;

    public TagController(RetrieveTagsUseCase retrieveTagsUseCase, RetrieveTagByIdUseCase retrieveTagByIdUseCase, RetrieveTagByNameUseCase retrieveTagByNameUseCase) {
        this.retrieveTagsUseCase = retrieveTagsUseCase;
        this.retrieveTagByIdUseCase = retrieveTagByIdUseCase;
        this.retrieveTagByNameUseCase = retrieveTagByNameUseCase;
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagDTO>> getAllTags() {
        logger.info("Fetching all tags");
        List<TagDTO> tags = retrieveTagsUseCase.execute();
        logger.info("Fetched {} tags", tags.size());
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable Long id) throws TagNotFoundException, IllegalArgumentException {
        logger.info("Fetching tag by ID: {}", id);
        TagDTO tag = retrieveTagByIdUseCase.execute(id);
        logger.info("Fetched tag: {}", tag);
        return ResponseEntity.ok(tag);
    }

    @GetMapping("/tags/search")
    public ResponseEntity<TagDTO> getTagByName(@RequestParam String name) throws TagNotFoundException, IllegalArgumentException {
        logger.info("Fetching tag by name: {}", name);
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name must not be null or empty");
        }
        TagDTO tag = retrieveTagByNameUseCase.execute(name);
        logger.info("Fetched tag: {}", tag);
        return ResponseEntity.ok(tag);
    }
}