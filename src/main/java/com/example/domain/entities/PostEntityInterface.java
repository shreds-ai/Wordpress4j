package com.example.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.example.domain.PostDTO;
import com.example.domain.value_objects.PostStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface PostEntity {
    Logger LOGGER = LoggerFactory.getLogger(PostEntity.class);

    UUID getId();
    String getTitle();
    String getContent();
    Long getAuthorId();
    PostStatus getStatus();
    LocalDateTime getDate();
    LocalDateTime getModified();
    String getSlug();
    String getCommentStatus();
    String getPingStatus();
    boolean isSticky();
    Long getFeaturedMediaId();
    String getExcerpt();
    List<Long> getCategoriesIds();
    List<Long> getTagsIds();
    Map<String, String> getLinks();
    default PostDTO formatPostForResponse(PostEntity post) {
        if (post == null) {
            LOGGER.warn("PostEntity.formatPostForResponse was called with null post");
            return null;
        }
        try {
            PostDTO dto = new PostDTO();
            dto.setId(post.getId());
            dto.setTitle(com.example.util.CommonUtils.renderHtml(post.getTitle()));
            dto.setContent(com.example.util.CommonUtils.renderHtml(post.getContent()));
            dto.setAuthorId(post.getAuthorId());
            dto.setStatus(post.getStatus());
            dto.setDate(post.getDate());
            dto.setModified(post.getModified());
            dto.setSlug(post.getSlug());
            dto.setCommentStatus(post.getCommentStatus());
            dto.setPingStatus(post.getPingStatus());
            dto.setSticky(post.isSticky());
            dto.setFeaturedMediaId(post.getFeaturedMediaId());
            dto.setExcerpt(com.example.util.CommonUtils.renderHtml(post.getExcerpt()));
            dto.setCategoriesIds(post.getCategoriesIds());
            dto.setTagsIds(post.getTagsIds());
            dto.setLinks(post.getLinks());
            return dto;
        } catch (Exception e) {
            LOGGER.error("Error processing post in PostEntity.formatPostForResponse", e);
            return null;
        }
    }
}