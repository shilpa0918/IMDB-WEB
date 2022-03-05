package com.web.imdb.exception;

public class MovieAlreadyExistException extends RuntimeException {
    private String message;

    public MovieAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }

    public MovieAlreadyExistException() {

    }
}
