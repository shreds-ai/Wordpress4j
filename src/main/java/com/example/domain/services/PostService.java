package com.example.domain.services;

import com.example.application.dtos.PostDetailsDTO;
import com.example.application.dtos.PostListDTO;
import com.example.domain.exceptions.PostNotFoundException;
import com.example.domain.value_objects.PostListFilter;
import com.example.domain.entities.PostEntity;
import java.util.List;

/**
 * Interface for post services that handle data retrieval and transformation for posts.
 */
public interface PostService {

    /**
     * Finds a post by its ID.
     * @param id The ID of the post to retrieve.
     * @return PostDetailsDTO containing post details.
     * @throws PostNotFoundException if the post is not found.
     */
    PostDetailsDTO findById(Long id) throws PostNotFoundException;

    /**
     * Finds a post by its slug.
     * @param slug The slug of the post.
     * @return PostDetailsDTO containing post details.
     * @throws PostNotFoundException if the post is not found.
     */
    PostDetailsDTO findBySlug(String slug) throws PostNotFoundException;

    /**
     * Retrieves a list of posts based on filter criteria.
     * @param filter The filtering criteria.
     * @return List of PostListDTO representing the posts.
     */
    List<PostListDTO> findAll(PostListFilter filter);

    /**
     * Converts a PostEntity to a PostDetailsDTO.
     * @param postEntity The entity to convert.
     * @return The converted DTO.
     */
    PostDetailsDTO convertToDTO(PostEntity postEntity);

    /**
     * Implementations should handle data access exceptions and entity not found scenarios gracefully.
     */
}