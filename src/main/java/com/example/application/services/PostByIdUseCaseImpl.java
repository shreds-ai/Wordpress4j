package com.example.application.services;

import com.example.application.ports.PostByIdInputPort;
import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.ports.PostRepositoryPort;
import com.example.domain.exceptions.PostNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PostByIdUseCaseImpl implements PostByIdInputPort {

    private final PostRepositoryPort postRepositoryPort;
    private static final Logger logger = LoggerFactory.getLogger(PostByIdUseCaseImpl.class);

    public PostByIdUseCaseImpl(PostRepositoryPort postRepositoryPort) {
        this.postRepositoryPort = postRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "posts", key = "#postId")
    public PostDetailsDTO getPostById(Long postId) throws PostNotFoundException {
        if (postId == null || postId < 1) {
            throw new IllegalArgumentException("Invalid post ID");
        }
        try {
            Optional<PostEntity> postEntity = postRepositoryPort.findById(postId);
            logger.debug("Fetching post with ID: " + postId);
            return postEntity.map(this::mapToPostDetailsDTO).orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + postId));
        } catch (Exception e) {
            logger.error("Error retrieving post with ID: " + postId, e);
            throw new PostNotFoundException("Error retrieving post", e);
        }
    }

    private PostDetailsDTO mapToPostDetailsDTO(PostEntity postEntity) {
        return new PostDetailsDTO(
            postEntity.getId(),
            postEntity.getDate(),
            postEntity.getDateGmt(),
            postEntity.getGuid(),
            postEntity.getRendered(),
            postEntity.getModified(),
            postEntity.getModifiedGmt(),
            postEntity.getSlug(),
            postEntity.getStatus(),
            postEntity.getType(),
            postEntity.getLink(),
            postEntity.getTitle(),
            postEntity.getContent(),
            postEntity.getExcerpt(),
            postEntity.getAuthor(),
            postEntity.getFeaturedMedia(),
            postEntity.getCommentStatus(),
            postEntity.getPingStatus(),
            postEntity.isSticky(),
            postEntity.getTemplate(),
            postEntity.getFormat(),
            postEntity.getMeta(),
            postEntity.getCategories(),
            postEntity.getTags(),
            postEntity.getLinks(),
            postEntity.getVersion()
        );
    }
}