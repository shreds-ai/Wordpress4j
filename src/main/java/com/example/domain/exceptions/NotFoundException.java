package com.example.domain.exceptions;

import com.example.domain.exceptions.PostNotFoundException;

public class NotFoundException extends PostNotFoundException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}