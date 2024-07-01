package ai.shreds.postservice.domain.exceptions;

import java.lang.Throwable;

public class DataRetrievalException extends RuntimeException {
    public DataRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}