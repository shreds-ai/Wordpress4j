package ai.shreds.wordpress4j.commentRetrieval.adapters.primary;

import ai.shreds.wordpress4j.commentRetrieval.application.dtos.CommentDTO;
import ai.shreds.wordpress4j.commentRetrieval.application.services.CommentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private final CommentApplicationService commentApplicationService;

    @Autowired
    public CommentController(CommentApplicationService commentService) {
        this.commentApplicationService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        logger.info("Fetching all comments");
        try {
            List<CommentDTO> comments = commentApplicationService.fetchAllComments();
            logger.info("Successfully fetched all comments");
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            logger.error("An error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        logger.info("Fetching comment with ID: {}", id);
        if (id == null || id <= 0) {
            logger.warn("Invalid ID supplied: {}", id);
            return ResponseEntity.badRequest().build();
        }
        try {
            CommentDTO comment = commentApplicationService.fetchCommentById(id);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            logger.error("An error occurred while fetching comment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}