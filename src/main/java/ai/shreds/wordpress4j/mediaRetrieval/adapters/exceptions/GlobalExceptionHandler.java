package ai.shreds.wordpress4j.mediaRetrieval.adapters.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import ai.shreds.wordpress4j.mediaRetrieval.domain.exceptions.MediaItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MediaItemNotFoundException.class)
    public ResponseEntity<Object> handleMediaItemNotFoundException(MediaItemNotFoundException ex, WebRequest request) {
        logger.error("Media item not found", ex);
        return new ResponseEntity<>(
            "Media item not found: " + ex.getMessage() + ". Please check the ID and try again.",
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        logger.error("Unexpected error", ex);
        return new ResponseEntity<>(
            "An unexpected error occurred: " + ex.getMessage() + ". Please contact support.",
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
        HttpStatus status = ex instanceof CannotGetJdbcConnectionException ? HttpStatus.BAD_GATEWAY : HttpStatus.SERVICE_UNAVAILABLE;
        logger.error("Database access error", ex);
        return new ResponseEntity<>(
            "Database access error: " + ex.getMessage() + ". Please check your network connection and try again.",
            status
        );
    }
}