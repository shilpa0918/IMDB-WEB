package com.web.imdb.exception;

public class CustomerNotFoundException extends RuntimeException {
    private String message;

    public CustomerNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public CustomerNotFoundException() {
    }
}