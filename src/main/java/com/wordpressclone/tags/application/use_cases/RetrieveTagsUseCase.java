package com.wordpressclone.tags.application.use_cases;

import com.wordpressclone.tags.domain.services.TagService;
import com.wordpressclone.tags.application.dtos.TagDTO;
import com.wordpressclone.tags.domain.entities.TagEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.NotNull;

public class RetrieveTagsUseCase {

    private final TagService tagService;
    private static final Logger logger = LoggerFactory.getLogger(RetrieveTagsUseCase.class);

    public RetrieveTagsUseCase(TagService tagService) {
        this.tagService = tagService;
    }

    public List<TagDTO> execute() {
        try {
            return retrieveAllTags();
        } catch (DataAccessException e) {
            logger.error("Data access exception occurred", e);
            throw new RuntimeException("Failed to retrieve tags", e);
        }
    }

    public List<TagDTO> retrieveAllTags() {
        try {
            List<TagEntity> tagEntities = tagService.retrieveAllTags();
            return tagEntities.stream()
                .map(tagEntity -> new TagDTO(tagEntity.getId(), validateInput(tagEntity.getName()), tagEntity.getSlug(), tagEntity.getDescription(), tagEntity.getCount()))
                .collect(Collectors.toList());
        } catch (DataAccessException e) {
            logger.error("Data access exception occurred while retrieving all tags", e);
            throw e;
        }
    }

    private String validateInput(@NotNull String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }
        return input.trim();
    }
}