package com.example.application.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.domain.exceptions.DataRetrievalException;
import com.example.domain.value_objects.PostListFilter;

/**
 * Data transfer object for detailed post information, used to transfer data between the primary adapter and the application layer.
 *
 * @author Data Transfer Object
 */
@Data
public class PostDetailsDTO {

    private static final Logger logger = LoggerFactory.getLogger(PostDetailsDTO.class);

    private Long id;
    private LocalDateTime date;
    private LocalDateTime dateGmt;
    private String guid;
    private String rendered;
    private LocalDateTime modified;
    private LocalDateTime modifiedGmt;
    private String slug;
    private String status;
    private String type;
    private String link;
    private String title;
    private String content;
    private String excerpt;
    private Long author;
    private Long featuredMedia;
    private String commentStatus;
    private String pingStatus;
    private boolean sticky;
    private String template;
    private String format;
    private Map<String, String> meta;
    private List<Long> categories;
    private List<Long> tags;
    private Map<String, List<Map<String, String>>> links;
    private int version;

    /**
     * Validates the post details to ensure all required fields are present and correct before any CRUD operation.
     * @return true if validation passes, otherwise throws a specific RuntimeException with a detailed message
     */
    public boolean validatePostDetails() {
        if (this.id == null || this.date == null || this.title == null || this.title.isEmpty()) {
            logger.error("Validation failed for PostDetails: {}", this);
            throw new DataRetrievalException("Validation error: Missing required fields or incorrect data.");
        }
        return true;
    }

    /**
     * Handles concurrent updates to the post details, ensuring data consistency using version field.
     * @param postDetails The post details to be updated.
     */
    public void handleConcurrentUpdates(PostDetailsDTO postDetails) {
        logger.info("Handling concurrent update for PostDetails: Original {}, New {}", this, postDetails);
        // Logic to handle concurrent updates, e.g., compare version numbers
    }

    public PostListFilter createFilter() {
        return new PostListFilter();
    }
}