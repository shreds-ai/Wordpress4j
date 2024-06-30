package com.example.domain.value_objects;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.domain.config.PostConfig;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostValue {

    private Long id;
    private String title;
    private String content;
    private String excerpt;
    private com.example.domain.value_objects.PostStatus status;
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

    public void validatePostStatus() throws com.example.domain.exceptions.InvalidPostStatusException {
        if (this.status == null) {
            logger.error(PostConfig.INVALID_STATUS_LOG + this.status);
            throw new com.example.domain.exceptions.InvalidPostStatusException(PostConfig.INVALID_STATUS_MESSAGE + this.status);
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("id", this.id);
        postMap.put("title", this.title);
        postMap.put("content", this.content);
        postMap.put("excerpt", this.excerpt);
        postMap.put("status", this.status == null ? null : this.status.name());
        postMap.put("commentStatus", this.commentStatus);
        postMap.put("pingStatus", this.pingStatus);
        postMap.put("postPassword", this.postPassword);
        postMap.put("postName", this.postName);
        postMap.put("toPing", this.toPing);
        postMap.put("pinged", this.pinged);
        postMap.put("postType", this.postType);
        postMap.put("postMimeType", this.postMimeType);
        postMap.put("commentCount", this.commentCount);
        postMap.put("postDate", this.postDate);
        postMap.put("postDateGmt", this.postDateGmt);
        postMap.put("postModified", this.postModified);
        postMap.put("postModifiedGmt", this.postModifiedGmt);
        postMap.put("postContentFiltered", this.postContentFiltered);
        postMap.put("postParent", this.postParent);
        postMap.put("guid", this.guid);
        postMap.put("menuOrder", this.menuOrder);
        return postMap;
    }
}