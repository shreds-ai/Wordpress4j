package ai.shreds.wordpress4j.tagRetrieval.adapters.secondary;

import ai.shreds.wordpress4j.tagRetrieval.domain.entities.TagEntity;
import ai.shreds.wordpress4j.tagRetrieval.domain.services.TagService;
import ai.shreds.wordpress4j.tagRetrieval.application.ports.TagOutputPort;
import ai.shreds.wordpress4j.tagRetrieval.application.dtos.TagDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagOutputAdapter implements TagOutputPort {

    private final TagService tagService;
    private static final Logger logger = LoggerFactory.getLogger(TagOutputAdapter.class);

    public TagOutputAdapter(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public TagDTO outputTagData(TagEntity tagEntity) {
        if (tagEntity == null) {
            logger.error("Received a null TagEntity for transformation");
            throw new IllegalArgumentException("TagEntity cannot be null");
        }
        logger.info("Transforming TagEntity to TagDTO: {}", tagEntity);
        TagDTO result = TagDTO.fromEntity(tagEntity);
        logger.info("Transformation result: {}", result);
        return result;
    }

}