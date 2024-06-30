package com.example.adapters.secondary;

import com.example.application.dtos.PostDetailsDTO;
import com.example.application.dtos.PostListDTO;
import com.example.application.ports.PostByIdInputPort;
import com.example.application.ports.PostBySlugInputPort;
import com.example.application.ports.PostListInputPort;
import com.example.domain.entities.PostEntity;
import com.example.domain.ports.PostRepositoryPort;
import com.example.domain.services.PostService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.ratelimit.RateLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;

@Component
public class PostAdapter {
    private static final Logger logger = LoggerFactory.getLogger(PostAdapter.class);
    private final PostRepositoryPort postRepositoryPort;
    private final PostService postService;

    @Autowired
    public PostAdapter(PostRepositoryPort postRepositoryPort, PostService postService) {
        this.postRepositoryPort = postRepositoryPort;
        this.postService = postService;
    }

    @Transactional
    @Cacheable("posts")
    @RateLimit(limit = 100, period = 60)
    public PostListDTO retrievePosts(@Valid PostListInputPort inputPort) {
        logger.debug("Entering retrievePosts with inputPort: {}", inputPort);
        List<PostDetailsDTO> posts = postService.findAll(inputPort.getPostListFilter());
        PostListDTO result = new PostListDTO.Builder().posts(posts).build();
        logger.debug("Exiting retrievePosts with result: {}", result);
        return result;
    }

    @Transactional
    @Cacheable("postDetails")
    @RateLimit(limit = 50, period = 60)
    public PostDetailsDTO retrievePostById(@Valid PostByIdInputPort inputPort) {
        logger.debug("Entering retrievePostById with ID: {}", inputPort.getId());
        Optional<PostDetailsDTO> post = postService.findById(inputPort.getId());
        PostDetailsDTO result = post.orElseThrow(() -> new RuntimeException("Post not found"));
        logger.debug("Exiting retrievePostById with result: {}", result);
        return result;
    }

    @Transactional
    @Cacheable("postDetailsBySlug")
    @RateLimit(limit = 50, period = 60)
    public PostDetailsDTO retrievePostBySlug(@Valid PostBySlugInputPort inputPort) {
        logger.debug("Entering retrievePostBySlug with slug: {}", inputPort.getSlug());
        Optional<PostDetailsDTO> post = postService.findBySlug(inputPort.getSlug());
        PostDetailsDTO result = post.orElseThrow(() -> new RuntimeException("Post not found"));
        logger.debug("Exiting retrievePostBySlug with result: {}", result);
        return result;
    }

    private void handleDatabaseExceptions(Exception e) {
        logger.error("Database error", e);
        throw new RuntimeException("Database error", e);
    }

    public PostDetailsDTO convertToDto(PostEntity postEntity) {
        if (postEntity == null) {
            logger.error("Attempted to convert a null PostEntity to DTO");
            throw new IllegalArgumentException("PostEntity cannot be null");
        }
        return postService.convertToDTO(postEntity);
    }

    public PostEntity convertToEntity(@Valid PostDetailsDTO dto) {
        if (dto == null) {
            logger.error("Attempted to convert a null DTO to entity");
            throw new IllegalArgumentException("DTO cannot be null");
        }
        PostEntity postEntity = new PostEntity();
        postEntity.setId(dto.getId());
        postEntity.setDate(dto.getDate());
        postEntity.setDateGmt(dto.getDateGmt());
        postEntity.setGuid(dto.getGuid());
        postEntity.setRendered(dto.getRendered());
        postEntity.setModified(dto.getModified());
        postEntity.setModifiedGmt(dto.getModifiedGmt());
        postEntity.setSlug(dto.getSlug());
        postEntity.setStatus(dto.getStatus());
        postEntity.setType(dto.getType());
        postEntity.setLink(dto.getLink());
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());
        postEntity.setExcerpt(dto.getExcerpt());
        postEntity.setAuthor(dto.getAuthor());
        postEntity.setFeaturedMedia(dto.getFeaturedMedia());
        postEntity.setCommentStatus(dto.getCommentStatus());
        postEntity.setPingStatus(dto.getPingStatus());
        postEntity.setSticky(dto.isSticky());
        postEntity.setTemplate(dto.getTemplate());
        postEntity.setFormat(dto.getFormat());
        postEntity.setMeta(dto.getMeta());
        postEntity.setCategories(dto.getCategories());
        postEntity.setTags(dto.getTags());
        postEntity.setLinks(dto.getLinks());
        postEntity.setVersion(dto.getVersion());
        return postEntity;
    }
}