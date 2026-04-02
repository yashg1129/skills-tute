package com.skills.tute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SkillRestControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> exception(BadCredentialsException ex) {
        return expectedFailedException(ex);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> exception(DuplicateResourceException ex) {
        return expectedFailedException(ex);
    }

    private ResponseEntity<String> expectedFailedException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
}
