package com.example.adapters.secondary;

import com.example.application.dtos.PostDetailsDTO;
import com.example.application.dtos.PostListDTO;
import com.example.application.ports.PostByIdInputPort;
import com.example.application.ports.PostBySlugInputPort;
import com.example.application.ports.PostListInputPort;
import com.example.domain.entities.PostEntity;
import com.example.domain.exceptions.PostNotFoundException;
import com.example.domain.ports.PostRepositoryPort;
import com.example.domain.services.PostService;
import com.example.domain.value_objects.PostListFilter;
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

    @Transactional(readOnly = true)
    @Cacheable(value = "postDetailsCache")
    public PostDetailsDTO convertToDto(PostEntity postEntity) {
        return postService.convertToDTO(postEntity);
    }

    @Transactional(readOnly = true)
    public PostEntity convertToEntity(@Valid PostDetailsDTO dto) {
        // Basic implementation for converting PostDetailsDTO to PostEntity
        PostEntity postEntity = new PostEntity();
        // Assuming the conversion logic is straightforward and can be done in a similar manner to the DTO conversion
        // This is a placeholder for the conversion logic
        return postEntity;
    }

    @Transactional(readOnly = true)
    public PostListDTO getPosts(PostListInputPort inputPort) {
        // Implementation for fetching posts based on inputPort criteria
        PostListFilter filter = inputPort.getFilter();
        return new PostListDTO(postService.findAll(filter));
    }

    @Transactional(readOnly = true)
    public PostDetailsDTO getPostById(PostByIdInputPort inputPort) {
        Long id = inputPort.getId();
        try {
            return postService.findById(id);
        } catch (PostNotFoundException e) {
            handleDatabaseExceptions(e);
            return null;
        }
    }

    @Transactional(readOnly = true)
    public PostDetailsDTO getPostBySlug(PostBySlugInputPort inputPort) {
        String slug = inputPort.getSlug();
        try {
            return postService.findBySlug(slug);
        } catch (PostNotFoundException e) {
            handleDatabaseExceptions(e);
            return null;
        }
    }

    private void handleDatabaseExceptions(PostNotFoundException e) {
        logger.error("Database exception occurred", e);
        // Additional handling logic or rethrowing the exception can be added here
    }
}