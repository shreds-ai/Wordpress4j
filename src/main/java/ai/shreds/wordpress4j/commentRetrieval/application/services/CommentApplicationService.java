package ai.shreds.wordpress4j.commentRetrieval.application.services;

import ai.shreds.wordpress4j.commentRetrieval.application.dtos.CommentDTO;
import ai.shreds.wordpress4j.commentRetrieval.application.ports.FetchCommentByIdUseCase;
import ai.shreds.wordpress4j.commentRetrieval.application.ports.FetchCommentsUseCase;
import ai.shreds.wordpress4j.commentRetrieval.domain.exceptions.CommentNotFoundException;
import ai.shreds.wordpress4j.commentRetrieval.domain.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentApplicationService implements FetchCommentsUseCase, FetchCommentByIdUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CommentApplicationService.class);
    private final CommentService commentService;

    @Autowired
    public CommentApplicationService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public List<CommentDTO> fetchAllComments() throws DataAccessException {
        try {
            return commentService.fetchAllComments();
        } catch (DataAccessException e) {
            logger.error("Failed to fetch all comments", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to access the database", e);
        }
    }

    @Override
    public CommentDTO fetchCommentById(Long id) throws CommentNotFoundException, DataAccessException {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
        try {
            return commentService.fetchCommentById(id)
                    .orElseThrow(() -> new CommentNotFoundException(id));
        } catch (DataAccessException e) {
            logger.error("Failed to fetch comment by ID: " + id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found", e);
        }
    }
}