package com.example.wordpressclone.domain.value_objects;

import lombok.Data;
import com.example.wordpressclone.domain.ports.CategoryValuePort;
import java.util.Objects;

@Data
public class CategoryValueObject implements CategoryValuePort {

    private String name;
    private String slug;

    public CategoryValueObject() {}

    public CategoryValueObject(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CategoryValueObject that = (CategoryValueObject) obj;
        return Objects.equals(name, that.name) && Objects.equals(slug, that.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, slug);
    }

    @Override
    public String toString() {
        return "CategoryValueObject{" +
               "name='" + name + '\'' +
               ", slug='" + slug + '\'' +
               '}';
    }
}