package com.example.carwash.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super(String.format("Username '%s' already exists", username));
    }
}
