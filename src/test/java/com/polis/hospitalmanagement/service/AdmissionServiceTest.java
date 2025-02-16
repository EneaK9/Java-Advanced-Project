package com.polis.hospitalmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.polis.hospitalmanagement.entity.Admission;
import com.polis.hospitalmanagement.repository.AdmissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * Unit test class for AdmissionService.
 * Uses JUnit and Mockito for mocking dependencies.
 */
@ExtendWith(MockitoExtension.class) // Enables Mockito annotations in this test class
class AdmissionServiceTest {

    @Mock // Mocks the AdmissionRepository (prevents actual database interaction)
    private AdmissionRepository admissionRepository;

    @InjectMocks // Automatically injects the mock repository into AdmissionService
    private AdmissionService admissionService;

    private Admission admission;

    /**
     * Initializes test data before each test runs.
     */
    @BeforeEach
    void setUp() {
        admission = new Admission();// Creates a new Admission entity
        admission.setId(1L);
    }

    /**
     * Test case: When an admission exists, it should be found by ID.
     */
    @Test
    void testFindById_ShouldReturnAdmission() {
        // Mocking repository behavior: When findById(1L) is called, return the admission object
        when(admissionRepository.findById(1L)).thenReturn(Optional.of(admission));

        // Call the service method
        Admission found = admissionService.findById(1L);
        // Assertions to verify expected behavior
        assertNotNull(found); // Ensure that a record is returned
        assertEquals(1L, found.getId()); // Verify that the returned ID matches

    }

    /**
     * Test case: When an admission does not exist, an exception should be thrown.
     */
    @Test
    void testFindById_ShouldThrowExceptionWhenNotFound() {
        // Mocking repository behavior: When findById(2L) is called, return an empty Optional
        when(admissionRepository.findById(2L)).thenReturn(Optional.empty());

        // Verify that calling findById(2L) throws a RuntimeException with the expected message
        Exception exception = assertThrows(RuntimeException.class, () -> admissionService.findById(2L));
        assertEquals("Admission not found", exception.getMessage());
    }

    /**
     * Test case: When saving an admission, it should return the saved admission.
     */
    @Test
    void testSaveAdmission_ShouldReturnSavedAdmission() {
        // Mocking repository behavior: When save() is called with any Admission, return the admission object
        when(admissionRepository.save(any(Admission.class))).thenReturn(admission);

        // Call the service method
        Admission saved = admissionService.saveAdmission(new Admission());

        // Assertions to verify expected behavior
        assertNotNull(saved); // Ensure that the saved admission is not null
        assertEquals(1L, saved.getId()); // Verify that the returned ID matches
    }
}
