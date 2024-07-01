package ai.shreds.postservice.application.use_cases;

import ai.shreds.postservice.application.dtos.PostDetailsDTO;
import ai.shreds.postservice.domain.exceptions.PostNotFoundException;

/**
 * Use case for fetching a post by its ID, providing detailed information about the post.
 * This interface defines the contract for fetching post details by ID.
 */
public interface PostByIdUseCase {

    /**
     * Executes the use case to fetch a post by its ID, returning detailed information about the post.
     * @param postId the ID of the post to retrieve
     * @return PostDetailsDTO containing the details of the post
     * @throws PostNotFoundException if the post cannot be found
     * This exception is thrown when no post matches the provided ID, ensuring that the caller can handle this scenario appropriately.
     */
    PostDetailsDTO getPostById(Long postId) throws PostNotFoundException;
}