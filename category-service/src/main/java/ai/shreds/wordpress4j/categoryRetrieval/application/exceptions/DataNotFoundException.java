package ai.shreds.wordpress4j.categoryRetrieval.application.exceptions;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}