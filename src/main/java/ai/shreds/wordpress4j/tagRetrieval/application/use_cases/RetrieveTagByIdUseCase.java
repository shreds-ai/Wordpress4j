package ai.shreds.wordpress4j.tagRetrieval.application.use_cases;

import ai.shreds.wordpress4j.tagRetrieval.domain.entities.TagEntity;
import ai.shreds.wordpress4j.tagRetrieval.domain.exceptions.TagNotFoundException;
import ai.shreds.wordpress4j.tagRetrieval.domain.ports.TagServicePort;
import ai.shreds.wordpress4j.tagRetrieval.application.dtos.TagDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RetrieveTagByIdUseCase {

    private final TagServicePort tagService;
    private static final Logger logger = LoggerFactory.getLogger(RetrieveTagByIdUseCase.class);

    public RetrieveTagByIdUseCase(TagServicePort tagService) {
        this.tagService = tagService;
    }

    public TagDTO execute(Long tagId) throws TagNotFoundException, IllegalArgumentException {
        validateTagId(tagId);
        TagEntity tagEntity = tagService.retrieveTagById(tagId);
        return TagDTO.fromEntity(tagEntity);
    }

    private void validateTagId(Long tagId) throws IllegalArgumentException {
        if (tagId == null || tagId < 1) {
            throw new IllegalArgumentException("Invalid Tag ID: " + tagId);
        }
        logger.debug("Validated tag ID: {}", tagId);
    }
}