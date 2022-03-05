package com.web.imdb.exception;

public class ActorNotExistException extends RuntimeException {

    private String message;

    public ActorNotExistException(String message) {
        super(message);
        this.message = message;
    }

    public ActorNotExistException() {

    }

}

