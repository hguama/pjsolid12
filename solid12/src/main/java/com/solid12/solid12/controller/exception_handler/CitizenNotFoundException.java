package com.solid12.solid12.controller.exception_handler;

public class CitizenNotFoundException extends RuntimeException {
    public CitizenNotFoundException(String message) {
        super(message);
    }
}
