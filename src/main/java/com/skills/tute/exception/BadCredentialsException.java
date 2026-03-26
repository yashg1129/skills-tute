package com.skills.tute.exception;

public class BadCredentialsException extends RuntimeException {
    private String message;

    public BadCredentialsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
