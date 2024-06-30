package com.example.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Data;
import com.example.domain.value_objects.PostStatus;

@Data
public class PostDTO {
    private UUID id;
    private String title;
    private String content;
    private Long authorId;
    private PostStatus status;
    private LocalDateTime date;
    private LocalDateTime modified;
    private String slug;
    private String commentStatus;
    private String pingStatus;
    private boolean sticky;
    private Long featuredMediaId;
    private String excerpt;
    private List<Long> categoriesIds;
    private List<Long> tagsIds;
    private Map<String, String> links;
}