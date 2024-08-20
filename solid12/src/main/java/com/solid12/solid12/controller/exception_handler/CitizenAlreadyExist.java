package com.solid12.solid12.controller.exception_handler;

public class CitizenAlreadyExist extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public CitizenAlreadyExist() {
        super("Citizen already exists on data base.");
    }
}
