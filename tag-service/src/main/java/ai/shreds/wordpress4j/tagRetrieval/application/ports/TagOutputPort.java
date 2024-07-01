package ai.shreds.wordpress4j.tagRetrieval.application.ports;

import ai.shreds.wordpress4j.tagRetrieval.domain.entities.TagEntity;
import ai.shreds.wordpress4j.tagRetrieval.application.dtos.TagDTO;

public interface TagOutputPort {

    public TagDTO outputTagData(TagEntity tagEntity);

}