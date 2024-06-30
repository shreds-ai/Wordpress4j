package com.example.application.dtos;

import java.util.List;
import lombok.Data;

/**
 * Encapsulates criteria for fetching posts by various filters.
 */
@Data
public class PostFilterCriteria {

    private String after;
    private String before;
    private List<Integer> author;
    private List<Integer> authorExclude;
    private List<String> status;

    public PostFilterCriteria(String after, String before, List<Integer> author, List<Integer> authorExclude, List<String> status) {
        this.after = after;
        this.before = before;
        this.author = author;
        this.authorExclude = authorExclude;
        this.status = status;
    }
}