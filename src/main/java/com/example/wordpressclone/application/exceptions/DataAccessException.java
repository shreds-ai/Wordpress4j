package com.example.wordpressclone.application.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }
}