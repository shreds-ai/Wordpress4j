package ai.shreds.wordpress4j.tagRetrieval.application.use_cases;

import ai.shreds.wordpress4j.tagRetrieval.domain.entities.TagEntity;
import ai.shreds.wordpress4j.tagRetrieval.domain.exceptions.TagNotFoundException;
import ai.shreds.wordpress4j.tagRetrieval.domain.ports.TagServicePort;
import ai.shreds.wordpress4j.tagRetrieval.application.dtos.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Use case for retrieving a tag by its name from the domain layer.
 */
@Component
public class RetrieveTagByNameUseCase {

    private final TagServicePort tagService;

    @Autowired
    public RetrieveTagByNameUseCase(TagServicePort tagService) {
        this.tagService = tagService;
    }

    public TagDTO execute(String tagName) throws TagNotFoundException, IllegalArgumentException {
        if (tagName == null || tagName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name must not be null or empty");
        }
        TagEntity tagEntity = tagService.retrieveTagByName(tagName);
        return TagDTO.fromEntity(tagEntity);
    }
}