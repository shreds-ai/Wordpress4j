package com.wordpressclone.tags.domain.value_objects;

import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class TagValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;
}