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

    public String toJson() throws RuntimeException {
        validateTagData();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting TagDTO to JSON", e);
        }
    }

    public static TagDTO fromJson(String json) throws RuntimeException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, TagDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to TagDTO", e);
        }
    }

    private void validateTagData() {
        if (id == null) throw new IllegalStateException("Tag ID is not set.");
        if (name == null) throw new IllegalStateException("Tag name is not set.");
        if (slug == null) throw new IllegalStateException("Tag slug is not set.");
        if (description == null) throw new IllegalStateException("Tag description is not set.");
        if (count == null) throw new IllegalStateException("Tag count is not set.");
    }
}