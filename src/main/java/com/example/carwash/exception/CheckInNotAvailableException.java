package com.example.carwash.exception;

public class CheckInNotAvailableException extends RuntimeException {
    public CheckInNotAvailableException() {
        super("Check in is not available already");
    }
}
