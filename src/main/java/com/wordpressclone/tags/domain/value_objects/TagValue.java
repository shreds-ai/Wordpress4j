package com.wordpressclone.tags.domain.value_objects;

import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TagValue implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String slug;
}