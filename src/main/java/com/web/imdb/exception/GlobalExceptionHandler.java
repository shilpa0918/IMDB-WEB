package com.web.imdb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    private String movieNotFoundMsg = "Movie with this name doesn't exist";
    private String movieAlreadyExistsMsg = "Movie with this name already exists";

    private String actorNotFoundMsg = "Actor name doesn't exist";
    private String actorAlreadyExistsMsg = "Actor with this email already exists. Please check again";

    private String reviewNotFoundMsg = "Review doesn't exist";
    private String reviewAlreadyExistsMsg = "Review  already exists";

    private String responseMsg = "response retrieved successfully";


    @ExceptionHandler(value = MovieNotExistException.class)
    public ResponseEntity movieNotFoundException(MovieNotExistException customerNotFoundException) {
        return new ResponseEntity(movieNotFoundMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MovieAlreadyExistException.class)
    public ResponseEntity movieAlreadyExistsException(MovieAlreadyExistException customerAlreadyExistsException) {
        return new ResponseEntity(movieAlreadyExistsMsg, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ActorAlreadyExistException.class)
    public ResponseEntity actorAlreadyExistsException(ActorAlreadyExistException actorAlreadyExistException) {
        return new ResponseEntity(actorAlreadyExistsMsg, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ActorNotExistException.class)
    public ResponseEntity actorNotExistException(ActorNotExistException actorNotExistException) {
        return new ResponseEntity(actorNotFoundMsg, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ReviewAlreadyExistException.class)
    public ResponseEntity reviewAlreadyExistsException(ReviewAlreadyExistException reviewAlreadyExistException) {
        return new ResponseEntity(reviewAlreadyExistsMsg, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ReviewNotExistException.class)
    public ResponseEntity reviewNotExistException(ReviewNotExistException reviewNotExistException) {
        return new ResponseEntity(reviewNotFoundMsg, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
        return new ResponseEntity(responseMsg, HttpStatus.ACCEPTED);
    }
}
