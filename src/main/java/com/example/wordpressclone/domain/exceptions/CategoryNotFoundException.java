package com.example.wordpressclone.domain.exceptions;

import java.io.Serial;

public class CategoryNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}