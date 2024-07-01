package ai.shreds.postservice.domain.exceptions;

public class InvalidPostStatusException extends RuntimeException {

    public InvalidPostStatusException(String message) {
        super(message);
    }
}