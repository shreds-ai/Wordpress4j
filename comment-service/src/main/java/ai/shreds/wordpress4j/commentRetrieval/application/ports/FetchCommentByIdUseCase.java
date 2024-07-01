package ai.shreds.wordpress4j.commentRetrieval.application.ports;

import ai.shreds.wordpress4j.commentRetrieval.application.dtos.CommentDTO;
import ai.shreds.wordpress4j.commentRetrieval.domain.exceptions.CommentNotFoundException;

/**
 * Input port defining the use case for fetching a specific comment by ID.
 * This interface is part of the application's core API, allowing for decoupled architecture and easier testing.
 */
public interface FetchCommentByIdUseCase {

    /**
     * Fetches a specific comment by its ID from the database and returns it as a CommentDTO.
     * Throws CommentNotFoundException if the comment is not found.
     *
     * @param id the ID of the comment to fetch
     * @return the fetched comment as a CommentDTO
     * @throws CommentNotFoundException if the comment with the specified ID does not exist
     */
    CommentDTO fetchCommentById(Long id) throws CommentNotFoundException;
}