package com.example.wordpressclone.domain.exceptions;

public class DatabaseTimeoutException extends Exception {
    public DatabaseTimeoutException(String message) {
        super(message);
    }
}