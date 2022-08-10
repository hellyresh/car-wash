package com.example.carwash.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, String paramName, String paramValue) {
        super(String.format("%s with %s = %s doesn't exist", entityName, paramName, paramValue));
    }
}
