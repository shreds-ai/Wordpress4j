package ai.shreds.postservice.domain.exceptions;

import java.lang.Throwable;

public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}