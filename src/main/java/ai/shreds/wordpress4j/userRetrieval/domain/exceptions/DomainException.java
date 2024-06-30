package ai.shreds.wordpress4j.userRetrieval.domain.exceptions;

import java.io.Serializable;

public class DomainException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}