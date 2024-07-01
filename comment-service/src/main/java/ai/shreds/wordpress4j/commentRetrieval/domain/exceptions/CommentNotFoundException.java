package ai.shreds.wordpress4j.commentRetrieval.domain.exceptions;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long id) {
        super("Comment not found with ID: " + id);
    }

}
