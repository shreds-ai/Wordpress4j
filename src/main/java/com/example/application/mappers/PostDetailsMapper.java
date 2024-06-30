package com.example.application.mappers;

import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.entities.PostEntity;

public class PostDetailsMapper {
    public static PostDetailsDTO toDTO(PostEntity postEntity) {
        if (postEntity == null) {
            return null;
        }
        PostDetailsDTO dto = new PostDetailsDTO();
        dto.setId(postEntity.getId());
        dto.setDate(postEntity.getPostDate());
        dto.setDateGmt(postEntity.getPostDateGmt());
        dto.setGuid(postEntity.getGuid());
        dto.setRendered(postEntity.renderHtmlContent(postEntity.getContent()));
        dto.setModified(postEntity.getPostModifiedDate());
        dto.setModifiedGmt(postEntity.getPostModifiedDateGmt());
        dto.setSlug(postEntity.getPostName());
        dto.setStatus(postEntity.getStatus().toString());
        dto.setType(postEntity.getPostType());
        dto.setLink(postEntity.getGuid()); // Assuming GUID is used as link
        dto.setTitle(postEntity.getTitle());
        dto.setContent(postEntity.getContent());
        dto.setExcerpt(postEntity.getExcerpt());
        dto.setAuthor(postEntity.getAuthor());
        dto.setFeaturedMedia(postEntity.getFeaturedMedia());
        dto.setCommentStatus(postEntity.getCommentStatus());
        dto.setPingStatus(postEntity.getPingStatus());
        dto.setSticky(postEntity.isSticky());
        dto.setTemplate(postEntity.getTemplate());
        dto.setFormat(postEntity.getFormat());
        dto.setMeta(postEntity.getMeta());
        dto.setCategories(postEntity.getCategories());
        dto.setTags(postEntity.getTags());
        dto.setLinks(postEntity.getLinks());
        dto.setVersion(postEntity.getVersion());
        return dto;
    }
}