package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.dto.PatientDTO;
import com.polis.hospitalmanagement.entity.Department;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.repository.DepartmentRepository;
import com.polis.hospitalmanagement.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for PatientService.
 * Uses JUnit and Mockito for mocking dependencies.
 */
class PatientServiceTest {

    @Mock // Mocks the PatientRepository (prevents actual database interaction)
    private PatientRepository patientRepository;

    @Mock // Mocks the DepartmentRepository
    private DepartmentRepository departmentRepository;

    @InjectMocks // Automatically injects the mock repositories into PatientService
    private PatientService patientService;

    /**
     * Initializes Mockito before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
    }

    /**
     * Test case: When a patient exists, it should be found by ID.
     */
    @Test
    void testGetPatientById() {
        // Arrange: Create a new patient object
        Patient patient = new Patient();
        patient.setId(1L); // Assign an ID
        patient.setFirstName("John"); // Assign a first name
        patient.setLastName("Doe"); // Assign a last name

        // Mocking repository behavior: When findById(1L) is called, return the patient object
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        // Act: Call the service method and convert the returned patient to a DTO
        PatientDTO result = new PatientDTO(patientService.getPatientById(1L));

        // Assert: Verify expected behavior
        assertNotNull(result); // Ensure that a patient is returned
        assertEquals("John", result.getFirstName()); // Verify first name
        assertEquals("Doe", result.getLastName()); // Verify last name
    }

    /**
     * Test case: When a patient does not exist, an exception should be thrown.
     */
    @Test
    void testGetPatientById_NotFound() {
        // Arrange: Mocking repository behavior to return empty when patient is not found
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert: Verify that calling getPatientById(1L) throws a RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            patientService.getPatientById(1L);
        });

        // Check that the exception message is correct
        assertEquals("Patient not found", exception.getMessage());
    }
}