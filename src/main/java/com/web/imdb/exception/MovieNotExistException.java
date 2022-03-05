package com.web.imdb.exception;

public class MovieNotExistException extends RuntimeException {
    private String message;

    public MovieNotExistException(String message) {
        super(message);
        this.message = message;
    }

    public MovieNotExistException() {

    }
}
