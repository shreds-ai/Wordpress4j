package ai.shreds.wordpress4j.commentRetrieval.domain.ports;

import ai.shreds.wordpress4j.commentRetrieval.domain.entities.CommentEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Interface defining the contract for comment data access.
 * Methods include fetching all comments and fetching a comment by ID.
 */
public interface CommentRepositoryPort {

    /**
     * Retrieves a list of all comments from the database.
     * @return List of CommentEntity
     * @throws DataAccessException if there is an issue with the database access
     */
    @Transactional(readOnly = true)
    List<CommentEntity> findAllComments() throws DataAccessException;

    /**
     * Retrieves a specific comment by its ID from the database.
     * @param id The ID of the comment to retrieve
     * @return Optional containing the found CommentEntity or empty if not found
     * @throws DataAccessException if the comment is not found or if there is a database error
     */
    @Transactional(readOnly = true)
    Optional<CommentEntity> findCommentById(Long id) throws DataAccessException;
}