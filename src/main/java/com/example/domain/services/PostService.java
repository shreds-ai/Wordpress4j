package com.example.domain.services;

import com.example.domain.entities.PostEntity;
import com.example.domain.exceptions.PostNotFoundException;
import com.example.domain.ports.PostListParameters;
import com.example.domain.ports.PostRepositoryPort;
import com.example.infrastructure.repositories.CustomPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for post services that handle data retrieval and transformation for posts,
 * implementing the PostRepositoryPort interface.
 */

@Service
public class PostService implements PostRepositoryPort {
    private final CustomPostRepository customPostRepository;

    public PostService(CustomPostRepository customPostRepository) {
        this.customPostRepository = customPostRepository;
    }

    @Override
    public Optional<PostEntity> findById(Long id) throws PostNotFoundException {
        return customPostRepository.findById(id);
    }

    @Override
    public Optional<PostEntity> findBySlug(String slug) throws PostNotFoundException {
        return customPostRepository.findBySlug(slug);
    }

    @Override
    public List<PostEntity> findAll(PostListParameters listParameters) {
        return customPostRepository.findAll(listParameters);
    }

}