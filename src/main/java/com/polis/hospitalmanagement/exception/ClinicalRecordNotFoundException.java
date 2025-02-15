package com.polis.hospitalmanagement.exception;

public class ClinicalRecordNotFoundException extends RuntimeException {
    public ClinicalRecordNotFoundException(String message) {
        super(message);
    }
}
