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

@ExtendWith(MockitoExtension.class)
class AdmissionServiceTest {

    @Mock
    private AdmissionRepository admissionRepository;

    @InjectMocks
    private AdmissionService admissionService;

    private Admission admission;

    @BeforeEach
    void setUp() {
        admission = new Admission();
        admission.setId(1L);
    }

    @Test
    void testFindById_ShouldReturnAdmission() {
        when(admissionRepository.findById(1L)).thenReturn(Optional.of(admission));

        Admission found = admissionService.findById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testFindById_ShouldThrowExceptionWhenNotFound() {
        when(admissionRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> admissionService.findById(2L));
        assertEquals("Admission not found", exception.getMessage());
    }

    @Test
    void testSaveAdmission_ShouldReturnSavedAdmission() {
        when(admissionRepository.save(any(Admission.class))).thenReturn(admission);

        Admission saved = admissionService.saveAdmission(new Admission());
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }
}
