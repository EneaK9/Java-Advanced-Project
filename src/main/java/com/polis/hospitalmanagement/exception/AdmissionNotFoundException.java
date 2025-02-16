package com.polis.hospitalmanagement.exception;

/**
 * Custom exception class for handling cases where an admission is not found.
 * Extends RuntimeException, so it can be thrown without explicit try-catch blocks.
 */
public class AdmissionNotFoundException extends RuntimeException {
    public AdmissionNotFoundException(String message) {
        super(message); // Calls the constructor of RuntimeException with a custom message
    }
}
