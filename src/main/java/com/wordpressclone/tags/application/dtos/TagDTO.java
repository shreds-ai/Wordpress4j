package com.wordpressclone.tags.application.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class TagDTO implements TagDTOInterface {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    private String slug;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    private Integer count;

    public String toJson() {
        validateTagData();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting TagDTO to JSON", e);
        }
    }

    public static TagDTO fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, TagDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to TagDTO", e);
        }
    }

    private void validateTagData() {
        if (id == null || name == null || slug == null || description == null || count == null) {
            throw new IllegalStateException("Tag data is not properly set.");
        }
    }
}