package ai.shreds.wordpress4j.mediaRetrieval.domain.exceptions;

public class MediaItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MediaItemNotFoundException(String message) {
        super(message);
    }

    public MediaItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MediaItemNotFoundException() {
        super("Media item not found");
    }
}