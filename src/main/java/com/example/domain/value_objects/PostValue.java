package com.example.domain.value_objects;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import com.example.domain.value_objects.PostStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.domain.port.PostOperations;
import com.example.domain.config.PostConfig;

@Data
@NoArgsConstructor
public class PostValue implements PostOperations {

    private Long id;
    private String title;
    private String content;
    private String excerpt;
    private PostStatus status;
    private String commentStatus;
    private String pingStatus;
    private String postPassword;
    private String postName;
    private String toPing;
    private String pinged;
    private String postType;
    private String postMimeType;
    private Integer commentCount;
    private LocalDateTime postDate;
    private LocalDateTime postDateGmt;
    private LocalDateTime postModified;
    private LocalDateTime postModifiedGmt;
    private String postContentFiltered;
    private Long postParent;
    private String guid;
    private Integer menuOrder;

    private static final Logger logger = LoggerFactory.getLogger(PostValue.class);

    @Override
    public void validatePostStatus() throws InvalidPostStatusException {
        try {
            PostStatus.valueOf(this.status.name());
        } catch (IllegalArgumentException e) {
            logger.error(PostConfig.INVALID_STATUS_LOG + this.status, e);
            throw new InvalidPostStatusException(PostConfig.INVALID_STATUS_MESSAGE + this.status);
        }
    }

    @Override
    public Map<String, Object> toMap() {
        try {
            if (id == null || title == null || status == null) {
                throw new IllegalArgumentException(PostConfig.MANDATORY_FIELDS_NULL);
            }
            Map<String, Object> postMap = new HashMap<>();
            postMap.put("id", id);
            postMap.put("title", title);
            postMap.put("content", content);
            postMap.put("excerpt", excerpt);
            postMap.put("status", status == null ? null : status.name());
            postMap.put("commentStatus", commentStatus);
            postMap.put("pingStatus", pingStatus);
            postMap.put("postPassword", postPassword);
            postMap.put("postName", postName);
            postMap.put("toPing", toPing);
            postMap.put("pinged", pinged);
            postMap.put("postType", postType);
            postMap.put("postMimeType", postMimeType);
            postMap.put("commentCount", commentCount);
            postMap.put("postDate", postDate);
            postMap.put("postDateGmt", postDateGmt);
            postMap.put("postModified", postModified);
            postMap.put("postModifiedGmt", postModifiedGmt);
            postMap.put("postContentFiltered", postContentFiltered);
            postMap.put("postParent", postParent);
            postMap.put("guid", guid);
            postMap.put("menuOrder", menuOrder);
        } catch (Exception e) {
            logger.error(PostConfig.ERROR_MAPPING_LOG, e);
            throw e;
        }
        return postMap;
    }

    private class InvalidPostStatusException extends RuntimeException {
        public InvalidPostStatusException(String message) {
            super(message);
        }
    }
}