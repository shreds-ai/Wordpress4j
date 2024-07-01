package ai.shreds.wordpress4j.commentRetrieval.application.ports;

import ai.shreds.wordpress4j.commentRetrieval.application.dtos.CommentDTO;
import org.springframework.dao.DataAccessException;
import java.util.List;

/**
 * Interface defining the use case for fetching all comments from the database.
 * Provides methods to retrieve all comments as CommentDTO objects.
 * Ensures decoupled architecture by interacting through the CommentRepositoryPort.
 */
public interface FetchCommentsUseCase {

    /**
     * Method to fetch all comments from the database and return them as a list of CommentDTO objects.
     * This method interacts with the domain layer through the CommentRepositoryPort to retrieve data,
     * ensuring decoupled architecture and adherence to hexagonal design principles.
     * It includes exception handling to manage potential data access issues effectively.
     *
     * @return List<CommentDTO>
     * @throws DataAccessException if there is an issue accessing the data
     */
    List<CommentDTO> fetchAllComments() throws DataAccessException;
}