package com.example.carwash.exception;

public class AuthException extends RuntimeException{
    public AuthException() {
        super("Wrong username or password");
    }

    public AuthException(String message) {
        super(message);
    }
}
