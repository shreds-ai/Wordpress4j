package ai.shreds.wordpress4j.userRetrieval.adapters.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom exception class for handling exceptions within adapter implementations.
 */
@Getter
@Setter
public class AdapterException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private static final Logger logger = LoggerFactory.getLogger(AdapterException.class);

    /**
     * Constructs a new AdapterException with the specified detail message.
     * @param message the detail message.
     */
    public AdapterException(String message) {
        super(message);
    }

    /**
     * Constructs a new AdapterException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause.
     */
    public AdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new AdapterException with the specified cause.
     * @param cause the cause.
     */
    public AdapterException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AdapterException with the specified message and error code.
     * @param message the detail message.
     * @param errorCode the error code associated with this exception.
     */
    public AdapterException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Logs the details of the exception using the logger.
     */
    public void logException() {
        logger.error("Error Code: " + errorCode + ", Message: " + getMessage());
    }
}