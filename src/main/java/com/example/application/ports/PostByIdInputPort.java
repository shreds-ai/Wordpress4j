package com.example.application.ports;

import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.exceptions.PostNotFoundException;
import java.util.List;

/**
 * Input contract for fetching a post by its ID. This interface serves as the entry point
 * for the primary adapters to interact with the use case for fetching post details.
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
    List<PostDetailsDTO> getPostsPaginated(int page, int size, String sort);
}