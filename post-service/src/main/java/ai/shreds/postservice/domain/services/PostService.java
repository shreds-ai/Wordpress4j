package ai.shreds.postservice.domain.services;

import ai.shreds.postservice.domain.entities.PostEntity;
import ai.shreds.postservice.domain.exceptions.PostNotFoundException;
import ai.shreds.postservice.domain.ports.PostListParameters;
import ai.shreds.postservice.domain.ports.PostRepositoryPort;
import postservice.example.infrastructure.repositories.CustomPostRepository;
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