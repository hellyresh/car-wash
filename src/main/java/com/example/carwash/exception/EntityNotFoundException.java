package com.example.carwash.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s with id = %d doesn't exist", entityName, id));
    }
}
