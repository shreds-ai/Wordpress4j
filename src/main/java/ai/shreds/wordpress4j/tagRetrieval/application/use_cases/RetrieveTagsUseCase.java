package ai.shreds.wordpress4j.tagRetrieval.application.use_cases;

import ai.shreds.wordpress4j.tagRetrieval.domain.entities.TagEntity;
import ai.shreds.wordpress4j.tagRetrieval.domain.ports.TagServicePort;
import ai.shreds.wordpress4j.tagRetrieval.application.dtos.TagDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RetrieveTagsUseCase {

    private final TagServicePort tagService;
    private static final Logger logger = LoggerFactory.getLogger(RetrieveTagsUseCase.class);

    public RetrieveTagsUseCase(TagServicePort tagService) {
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
                    .map(TagDTO::fromEntity)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            logger.error("Data access exception occurred while retrieving all tags", e);
            throw e;
        }
    }

}