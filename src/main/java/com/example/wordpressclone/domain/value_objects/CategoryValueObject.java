package com.example.wordpressclone.domain.value_objects;

import lombok.Data;
import com.example.wordpressclone.domain.ports.CategoryValuePort;

@Data
public class CategoryValueObject implements CategoryValuePort {

    private String name;
    private String slug;

    public CategoryValueObject() {}

    public CategoryValueObject(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }
}