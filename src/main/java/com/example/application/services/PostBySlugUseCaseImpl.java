package com.example.application.services;

import com.example.application.dtos.PostDetailsDTO;
import com.example.application.mappers.PostDetailsMapper;
import com.example.application.use_cases.PostBySlugUseCase;
import com.example.domain.entities.PostEntity;
import com.example.domain.exceptions.PostNotFoundException;
import com.example.domain.ports.PostRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostBySlugUseCaseImpl implements PostBySlugUseCase {
    private static final Logger logger = LoggerFactory.getLogger(PostBySlugUseCaseImpl.class);
    private final PostRepositoryPort postRepositoryPort;

    @Value("${application.base-url}")
    private String baseUrl;


    public PostBySlugUseCaseImpl(PostRepositoryPort postRepositoryPort) {
        this.postRepositoryPort = postRepositoryPort;
    }

    @Override
    public PostDetailsDTO fetchPostBySlug(String slug) throws PostNotFoundException {
        Optional<PostEntity> postEntity = postRepositoryPort.findBySlug(slug);
        if (!postEntity.isPresent()) {
            throw new PostNotFoundException("Post not found with slug '" + slug + "'");
        }
        return PostDetailsMapper.toDTO(postEntity.get(), baseUrl);
    }
}