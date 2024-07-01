package ai.shreds.postservice.application.use_cases;

import ai.shreds.postservice.application.dtos.PostDetailsDTO;
import ai.shreds.postservice.domain.exceptions.DataRetrievalException;

/**
 * Use case for fetching a post by its slug, providing detailed information about the post.
 * This interface extends PostBySlugInputPort to ensure decoupling and adherence to hexagonal architecture.
 */
public interface PostBySlugUseCase  {
    PostDetailsDTO fetchPostBySlug(String slug) throws DataRetrievalException;
}