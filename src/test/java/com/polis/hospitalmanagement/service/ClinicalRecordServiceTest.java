package com.polis.hospitalmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.polis.hospitalmanagement.entity.ClinicalRecord;
import com.polis.hospitalmanagement.repository.ClinicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * Unit test class for ClinicalRecordService.
 * Uses JUnit and Mockito for mocking dependencies.
 */
@ExtendWith(MockitoExtension.class) // Enables Mockito annotations in this test class
class ClinicalRecordServiceTest {

    @Mock // Mocks the ClinicalRecordRepository (prevents actual database interaction)
    private ClinicalRecordRepository clinicalRecordRepository;

    @InjectMocks // Automatically injects the mock repository into ClinicalRecordService
    private ClinicalRecordService clinicalRecordService;

    private ClinicalRecord clinicalRecord; // Placeholder for a ClinicalRecord entity

    /**
     * Initializes test data before each test runs.
     */
    @BeforeEach
    void setUp() {
        clinicalRecord = new ClinicalRecord(); // Creates a new ClinicalRecord entity
        clinicalRecord.setId(1L); // Assigns an ID of 1L
        clinicalRecord.setClinicalNotes("Patient diagnosed with flu."); // Assigns clinical notes
    }

    /**
     * Test case: When a clinical record exists, it should be found by ID.
     */
    @Test
    void testFindById_ShouldReturnClinicalRecord() {
        // Mocking repository behavior: When findById(1L) is called, return the clinicalRecord object
        when(clinicalRecordRepository.findById(1L)).thenReturn(Optional.of(clinicalRecord));

        // Call the service method
        ClinicalRecord found = clinicalRecordService.findById(1L);

        // Assertions to verify expected behavior
        assertNotNull(found); // Ensure that a record is returned
        assertEquals("Patient diagnosed with flu.", found.getClinicalNotes()); // Verify that the clinical notes match
    }

    /**
     * Test case: When a clinical record does not exist, an exception should be thrown.
     */
    @Test
    void testFindById_ShouldThrowExceptionWhenNotFound() {
        // Mocking repository behavior: When findById(2L) is called, return an empty Optional
        when(clinicalRecordRepository.findById(2L)).thenReturn(Optional.empty());

        // Verify that calling findById(2L) throws a RuntimeException with the expected message
        Exception exception = assertThrows(RuntimeException.class, () -> clinicalRecordService.findById(2L));
        assertEquals("Clinical Record not found", exception.getMessage());
    }

    /**
     * Test case: When saving a clinical record, it should return the saved record.
     */
    @Test
    void testSaveClinicalRecord_ShouldReturnSavedRecord() {
        ClinicalRecord inputRecord = new ClinicalRecord(); // Create a new clinical record
        inputRecord.setClinicalNotes("Patient diagnosed with flu."); // Set clinical notes

        // Mocking repository behavior: When save() is called with any ClinicalRecord, return the input record
        when(clinicalRecordRepository.save(any(ClinicalRecord.class))).thenReturn(inputRecord);

        // Call the service method
        ClinicalRecord saved = clinicalRecordService.saveClinicalRecord(inputRecord);

        // Assertions to verify expected behavior
        assertNotNull(saved); // Ensure that the saved clinical record is not null
        assertEquals("Patient diagnosed with flu.", saved.getClinicalNotes()); // Verify that the clinical notes match
    }
}