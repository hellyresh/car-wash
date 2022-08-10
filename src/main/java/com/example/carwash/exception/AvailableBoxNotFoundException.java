package com.example.carwash.exception;

public class AvailableBoxNotFoundException extends RuntimeException {
    public AvailableBoxNotFoundException() {
        super("There is no available box at this time");
    }
}
