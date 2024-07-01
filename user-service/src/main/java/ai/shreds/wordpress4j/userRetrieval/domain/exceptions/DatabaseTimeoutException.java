package ai.shreds.wordpress4j.userRetrieval.domain.exceptions;

public class DatabaseTimeoutException extends Exception {
    public DatabaseTimeoutException(String message) {
        super(message);
    }
}