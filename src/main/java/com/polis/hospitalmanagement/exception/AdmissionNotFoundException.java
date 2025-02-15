package com.polis.hospitalmanagement.exception;

public class AdmissionNotFoundException extends RuntimeException {
    public AdmissionNotFoundException(String message) {
        super(message);
    }
}
