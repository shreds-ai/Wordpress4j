package com.example.wordpressclone.domain.exceptions;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidEmailException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(InvalidEmailException.class);

    public InvalidEmailException(String message) {
        super(message);
        logExceptionDetails();
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
        logExceptionDetails();
    }

    private void logExceptionDetails() {
        logger.error("Exception: " + getMessage(), this);
    }
}