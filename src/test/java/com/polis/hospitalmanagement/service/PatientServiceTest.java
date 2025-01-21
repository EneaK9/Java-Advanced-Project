package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePatient() {
        // Arrange
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");

        when(patientRepository.save(patient)).thenReturn(patient);

        // Act
        Patient result = patientService.createPatient(patient);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void testGetPatientById_NotFound() {
        // Arrange
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            patientService.getPatientById(1L);
        });

        assertEquals("Patient not found", exception.getMessage());
    }
}

