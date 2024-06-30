package com.example.wordpressclone.domain.exceptions;

public class MediaItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MediaItemNotFoundException(String message) {
        super(message);
    }

    public MediaItemNotFoundException() {
        super("Media item not found");
    }
}