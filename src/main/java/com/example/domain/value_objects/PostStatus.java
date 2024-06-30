package com.example.domain.value_objects;

public enum PostStatus {
    PUBLISH("Status indicating that a post is published and available for viewing."),
    FUTURE("Status indicating that a post is scheduled for future publication."),
    DRAFT("Status indicating that a post is in draft form and not yet ready for publication."),
    PENDING("Status indicating that a post is pending review before publication."),
    PRIVATE("Status indicating that a post is private and not publicly accessible.");

    private final String description;

    PostStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}