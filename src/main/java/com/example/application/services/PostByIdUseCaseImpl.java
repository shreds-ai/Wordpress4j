package com.example.application.services;

import com.example.application.dtos.PostDetailsDTO;
import com.example.application.mappers.PostDetailsMapper;
import com.example.application.use_cases.PostByIdUseCase;
import com.example.domain.entities.PostEntity;
import com.example.domain.exceptions.PostNotFoundException;
import com.example.domain.ports.PostRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class PostByIdUseCaseImpl implements PostByIdUseCase {
    private static final Logger logger = LoggerFactory.getLogger(PostByIdUseCaseImpl.class);
    private final PostRepositoryPort postRepositoryPort;

    @Value("${application.base-url}")
    private String baseUrl;


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
            return postEntity.map(pe -> PostDetailsMapper.toDTO(postEntity.get(), baseUrl)).orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + postId));} catch (Exception e) {
            logger.error("Error retrieving post with ID: " + postId, e);
            throw new PostNotFoundException("Error retrieving post", e);
        }
    }


}