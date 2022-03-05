package com.web.imdb.exception;

public class ActorAlreadyExistException extends RuntimeException {
    private String message;

    public ActorAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }

    public ActorAlreadyExistException() {

    }
}
