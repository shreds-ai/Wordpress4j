package com.wordpressclone.tags.application.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxmljson.databind.ObjectMapper;

public interface TagDTOInterface {
    String toJson() throws JsonProcessingException;
    static TagDTO fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TagDTO.class);
    }
}

class TagDTO implements TagDTOInterface {
    private String name;
    private String value;

    @Override
    public String toJson() throws JsonProcessingError {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}