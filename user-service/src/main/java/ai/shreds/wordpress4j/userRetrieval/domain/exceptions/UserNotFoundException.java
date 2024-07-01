package ai.shreds.wordpress4j.userRetrieval.domain.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}