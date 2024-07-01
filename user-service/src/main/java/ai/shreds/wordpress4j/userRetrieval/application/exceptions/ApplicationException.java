package ai.shreds.wordpress4j.userRetrieval.application.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Custom exception class for handling exceptions specific to the application layer.
 * It provides more context about errors that occur during the use case processing.
 */
@Getter
@Setter
public class ApplicationException extends Exception {

    private String message;
    private Throwable cause;

    /**
     * Constructor for ApplicationException with a message parameter.
     *
     * @param message the detail message.
     */
    public ApplicationException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Constructor for ApplicationException with message and cause parameters.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public Throwable getCause() {
        return super.getCause();
    }

    /**
     * Logs the stack trace of the exception to the system's error stream.
     */
    public void logStackTrace() {
        super.printStackTrace(System.err);
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
               "message='" + message + '\'' +
               ", cause=" + cause +
               '}';
    }
}