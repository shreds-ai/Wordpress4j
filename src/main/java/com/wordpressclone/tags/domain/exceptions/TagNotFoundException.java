package com.wordpressclone.tags.domain.exceptions;

import java.io.Serializable;

/**
 * Exception thrown when a tag is not found in the repository.
 * This class handles not found exceptions specifically related to tag operations in the domain layer.
 */
public class TagNotFoundException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new TagNotFoundException with no detail message.
     */
    public TagNotFoundException() {
        super();
    }

    /**
     * Constructs a new TagNotFoundException with the specified detail message.
     * @param message the detail message
     */
    public TagNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new TagNotFoundException with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public TagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new TagNotFoundException with the specified cause.
     * @param cause the cause of the exception
     */
    public TagNotFoundException(Throwable cause) {
        super(cause == null ? null : cause.toString(), cause);
    }
}