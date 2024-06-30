package com.example.application.use_cases;

import com.example.application.ports.PostBySlugInputPort;
import com.example.application.dtos.PostDetailsDTO;
import java.util.List;
import java.util.Optional;

/**
 * Use case for fetching a post by its slug, providing detailed information about the post.
 * This interface extends PostBySlugInputPort to ensure decoupling and adherence to hexagonal architecture.
 */
public interface PostBySlugUseCase extends PostBySlugInputPort {

    /**
     * Retrieves a post by its slug and returns detailed information about the post in the form of a PostDetailsDTO.
     * This method takes a slug as a parameter and delegates the request to the application layer for processing.
     * @param slug the slug of the post to retrieve
     * @return PostDetailsDTO containing detailed information about the post
     * @throws DataRetrievalException if the post cannot be found or if there is an issue with data access
     */
    PostDetailsDTO fetchPostBySlug(String slug) throws DataRetrievalException;

    /**
     * Method to handle pagination and sorting parameters for fetching posts.
     * This method ensures efficient data retrieval and aligns with the shred's requirements for handling large volumes of data.
     * @param page the page number of the results
     * @param size the number of posts per page
     * @param sort the sorting criteria
     * @return a list of PostDetailsDTO based on the provided parameters
     * @throws DatabaseConnectionException if there is an issue connecting to the database
     * @throws NotFoundException if no posts match the search criteria
     */
    List<PostDetailsDTO> fetchPosts(int page, int size, String sort) throws DatabaseConnectionException, NotFoundException;
}