package com.example.domain.exceptions;

public class InvalidPostStatusException extends RuntimeException {

    public InvalidPostStatusException(String message) {
        super(message);
    }
}