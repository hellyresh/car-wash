package com.example.carwash.exception;

public class CheckInNotAvailableException extends RuntimeException {
    public CheckInNotAvailableException(String message) {
        super("Check in is not available " + message);
    }
}
