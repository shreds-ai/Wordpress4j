package com.example.application.use_cases;

import com.example.application.ports.PostBySlugInputPort;
import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.exceptions.DataRetrievalException;

/**
 * Use case for fetching a post by its slug, providing detailed information about the post.
 * This interface extends PostBySlugInputPort to ensure decoupling and adherence to hexagonal architecture.
 */
public interface PostBySlugUseCase extends PostBySlugInputPort {
    PostDetailsDTO fetchPostBySlug(String slug) throws DataRetrievalException;
}