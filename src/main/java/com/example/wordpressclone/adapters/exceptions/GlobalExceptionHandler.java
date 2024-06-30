package com.example.wordpressclone.adapters.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.wordpressclone.domain.exceptions.CategoryNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {
        logger.error("Category not found exception: " + ex.getMessage() + " at " + request.getDescription(false), ex);
        return ResponseEntity.status(404).body(ErrorResponseFactory.create("Category not found", ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        logger.error("General exception: " + ex.getMessage() + " at " + request.getDescription(false), ex);
        return ResponseEntity.status(500).body(ErrorResponseFactory.create("Internal server error", ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        logger.error("Illegal argument exception: " + ex.getMessage() + " at " + request.getDescription(false), ex);
        return ResponseEntity.status(400).body(ErrorResponseFactory.create("Bad request", ex.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
        logger.error("Database access exception: " + ex.getMessage() + " at " + request.getDescription(false), ex);
        return ResponseEntity.status(500).body(ErrorResponseFactory.create("Database error", ex.getMessage(), System.currentTimeMillis()));
    }
}

interface ErrorResponseFactory {
    static ErrorResponse create(String error, String message, long timestamp) {
        return new ErrorResponse(error, message, timestamp);
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