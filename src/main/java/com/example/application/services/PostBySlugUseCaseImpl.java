package com.example.application.services;

import com.example.application.use_cases.PostBySlugUseCase;
import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.ports.PostRepositoryPort;
import com.example.domain.exceptions.PostNotFoundException;
import com.example.domain.entities.PostEntity;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.annotation.Timed;

@Service
@Validated
public class PostBySlugUseCaseImpl implements PostBySlugUseCase {
    private static final Logger logger = LoggerFactory.getLogger(PostBySlugUseCaseImpl.class);
    private final PostRepositoryPort postRepositoryPort;

    public PostBySlugUseCaseImpl(PostRepositoryPort postRepositoryPort) {
        this.postRepositoryPort = postRepositoryPort;
    }

    @Override
    public PostDetailsDTO execute(String slug) throws PostNotFoundException {
        Optional<PostEntity> postEntity = postRepositoryPort.findBySlug(slug);
        if (!postEntity.isPresent()) {
            throw new PostNotFoundException("Post with slug '" + slug + "' not found.");
        }
        return convertToPostDetailsDTO(postEntity.get());
    }

    private PostDetailsDTO convertToPostDetailsDTO(PostEntity postEntity) {
        // Conversion logic from PostEntity to PostDetailsDTO
        // This is a placeholder for the actual conversion logic
        throw new UnsupportedOperationException("Conversion logic not implemented.");
    }
}