package com.skills.tute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class SkillRestControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> exception(BadCredentialsException ex) {
        return expectedFailedException(ex);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> exception(DuplicateResourceException ex) {
        return expectedFailedException(ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> exception(AccessDeniedException ex) {
        return getResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidQuestionStateException.class)
    public ResponseEntity<?> exception(InvalidQuestionStateException ex) {
        return getResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> getResponseEntity(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(message, httpStatus);
    }

    private ResponseEntity<?> expectedFailedException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
}
