package com.wordpressclone.tags.application.ports;

import com.wordpressclone.tags.application.dtos.TagDTO;
import java.util.List;
import java.util.Objects;

/**
 * Defines the output port for sending tag data from the application layer to external interfaces or secondary adapters.
 */
@FunctionalInterface
public interface TagOutputPort {

    /**
     * Sends the tag data encapsulated in a TagDTO to an external interface or system.
     * @param tagDTO the tag data transfer object
     * @throws IllegalArgumentException if tagDTO is null
     */
    void sendTagData(TagDTO tagDTO);

    /**
     * Sends a batch of tag data, encapsulated in a list of TagDTOs, to an external interface or system.
     * @param tagDTOs the list of tag data transfer objects
     * @throws IllegalArgumentException if tagDTOs is null
     */
    void sendBatchTagData(List<TagDTO> tagDTOs);
}