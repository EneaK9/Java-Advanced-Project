package com.polis.hospitalmanagement.exception;

/**
 * Custom exception class for handling cases where a department is not found.
 * Extends RuntimeException, so it can be thrown without explicit try-catch blocks.
 */
public class DepartmentNotFoundException extends RuntimeException {
    /**
     * Constructor that takes a message and passes it to the parent RuntimeException class.
     * @param message The error message to be displayed when the exception is thrown.
     */
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}

