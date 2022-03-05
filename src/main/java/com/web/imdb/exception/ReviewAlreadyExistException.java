package com.web.imdb.exception;

public class ReviewAlreadyExistException extends RuntimeException {
    private String message;

    public ReviewAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }

    public ReviewAlreadyExistException() {

    }
}
