package com.wordpressclone.tags.adapters.secondary;

import com.wordpressclone.tags.application.ports.TagOutputPort;
import com.wordpressclone.tags.domain.entities.TagEntity;
import com.wordpressclone.tags.domain.services.TagService;
import com.wordpressclone.tags.application.dtos.TagDTO;
import lombok.Data;
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
        logger.info("Transforming TagEntity to TagDTO", tagEntity);
        TagDTO result = new TagDTO(
            tagEntity.getId(),
            tagEntity.getName(),
            tagEntity.getSlug(),
            tagEntity.getDescription(),
            tagEntity.getPostCount()
        );
        logger.info("Transformation result: {}", result);
        return result;
    }
}