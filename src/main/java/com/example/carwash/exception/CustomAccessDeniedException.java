package com.example.carwash.exception;

public class CustomAccessDeniedException extends RuntimeException {
    public CustomAccessDeniedException() {
        super("Access denied");
    }
}
