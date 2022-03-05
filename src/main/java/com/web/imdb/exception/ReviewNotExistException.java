package com.web.imdb.exception;

public class ReviewNotExistException extends RuntimeException {
    private String message;

    public ReviewNotExistException(String message) {
        super(message);
        this.message = message;
    }

    public ReviewNotExistException() {

    }
}
