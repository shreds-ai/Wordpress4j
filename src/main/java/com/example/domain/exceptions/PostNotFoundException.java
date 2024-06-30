package com.example.domain.exceptions;

import java.lang.RuntimeException;

public class PostNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PostNotFoundException() {
        super();
    }

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}