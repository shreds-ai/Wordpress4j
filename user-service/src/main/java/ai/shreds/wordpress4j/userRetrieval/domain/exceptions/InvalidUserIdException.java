package ai.shreds.wordpress4j.userRetrieval.domain.exceptions;

public class InvalidUserIdException extends Exception {
    public InvalidUserIdException(String message) {
        super(message);
    }
}