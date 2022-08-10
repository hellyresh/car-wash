package com.example.carwash.exception;

public class BoxAlreadyHasOperatorException extends RuntimeException {
    public BoxAlreadyHasOperatorException(Long id) {
        super(String.format("Box with id = %d already has operator", id));
    }
}
