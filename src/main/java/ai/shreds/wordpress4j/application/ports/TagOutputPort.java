package ai.shreds.wordpress4j.application.ports;

import ai.shreds.wordpress4j.domain.entities.TagEntity;
import ai.shreds.wordpress4j.application.dtos.TagDTO;

public interface TagOutputPort {

    public TagDTO outputTagData(TagEntity tagEntity);

}