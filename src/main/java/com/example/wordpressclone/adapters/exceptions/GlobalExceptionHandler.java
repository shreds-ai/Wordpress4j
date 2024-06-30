package com.example.wordpressclone.adapters.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.wordpressclone.application.exceptions.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.wordpressclone.domain.exceptions.CategoryNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {
        logger.error("Category not found exception: " + ex.getMessage() + " at " + request.getDescription(false), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Category not found", ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        logger.error("General exception: " + ex.getMessage() + " at " + request.getDescription(false), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal server error", ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        logger.error("Illegal argument exception: " + ex.getMessage() + " at " + request.getDescription(false), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Bad request", ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
        logger.error("Database access exception: " + ex.getMessage() + " at " + request.getDescription(false), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Database error", ex.getMessage(), System.currentTimeMillis()));
    }
}

class ErrorResponse {
    private String error;
    private String message;
    private long timestamp;

    public ErrorResponse(String error, String message, long timestamp) {
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}