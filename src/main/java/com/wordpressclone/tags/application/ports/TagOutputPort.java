package com.wordpressclone.tags.application.ports;

import com.wordpressclone.tags.application.dtos.TagDTO;
import java.util.List;

public class TagOutputPort {

    public void sendTagData(TagDTO tagDTO) {
        // Implementation for sending single tag data
    }

    public void sendBatchTagData(List<TagDTO> tagDTOs) {
        // Implementation for sending batch tag data
    }
}