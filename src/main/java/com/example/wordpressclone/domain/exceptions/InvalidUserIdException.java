package com.example.wordpressclone.domain.exceptions;

public class InvalidUserIdException extends Exception {
    public InvalidUserIdException(String message) {
        super(message);
    }
}