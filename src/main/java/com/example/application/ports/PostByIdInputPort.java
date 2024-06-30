package com.example.application.ports;

import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.exceptions.PostNotFoundException;

/**
 * Input contract for fetching a post by its ID. This interface serves as the entry point
 * for the primary adapters to interact with the use case for fetching post details.
 * 
 * <p>Use this interface to initiate fetching a post by its ID from the primary adapter,
 * such as the PostController. It is crucial for implementing the core functionalities
 * described in the project's specifications.</p>
 *
 * @see PostDetailsDTO
 * @see PostNotFoundException
 */
public interface PostByIdInputPort {

    /**
     * Method to initiate the fetching of a post by its ID. This method is expected to be called by
     * the primary adapter, such as the PostController. It returns the PostDetailsDTO containing the
     * details of the post if found, otherwise throws PostNotFoundException.
     *
     * @param postId the ID of the post to retrieve
     * @return PostDetailsDTO containing the details of the post
     * @throws PostNotFoundException if the post cannot be found
     * 
     * <p>Implementations should handle PostNotFoundException to ensure that the error is managed
     * gracefully, providing a clear message to the caller.</p>
     */
    PostDetailsDTO getPostById(Long postId) throws PostNotFoundException;

    /**
     * Method to support pagination and sorting when fetching posts.
     * This method enhances performance and efficiency when dealing with large data sets.
     *
     * @param page the page number of the results
     * @param size the number of posts per page
     * @param sort the sorting criteria (e.g., date, title)
     * @return a paginated list of posts sorted according to the specified criteria
     */
    PostDetailsDTO getPostsPaginated(int page, int size, String sort);
}