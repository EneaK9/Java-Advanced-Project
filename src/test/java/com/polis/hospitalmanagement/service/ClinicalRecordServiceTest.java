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

@ExtendWith(MockitoExtension.class)
class ClinicalRecordServiceTest {

    @Mock
    private ClinicalRecordRepository clinicalRecordRepository;

    @InjectMocks
    private ClinicalRecordService clinicalRecordService;

    private ClinicalRecord clinicalRecord;

    @BeforeEach
    void setUp() {
        clinicalRecord = new ClinicalRecord();
        clinicalRecord.setId(1L);
        clinicalRecord.setClinicalNotes("Patient diagnosed with flu.");  // Fix here
    }


    @Test
    void testFindById_ShouldReturnClinicalRecord() {
        when(clinicalRecordRepository.findById(1L)).thenReturn(Optional.of(clinicalRecord));

        ClinicalRecord found = clinicalRecordService.findById(1L);
        assertNotNull(found);
        assertEquals("Patient diagnosed with flu.", found.getClinicalNotes());  // Fix here
    }

    @Test
    void testFindById_ShouldThrowExceptionWhenNotFound() {
        when(clinicalRecordRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> clinicalRecordService.findById(2L));
        assertEquals("Clinical Record not found", exception.getMessage());
    }

    @Test
    void testSaveClinicalRecord_ShouldReturnSavedRecord() {
        ClinicalRecord inputRecord = new ClinicalRecord();
        inputRecord.setClinicalNotes("Patient diagnosed with flu.");  // Fix here

        when(clinicalRecordRepository.save(any(ClinicalRecord.class))).thenReturn(inputRecord);

        ClinicalRecord saved = clinicalRecordService.saveClinicalRecord(inputRecord);
        assertNotNull(saved);
        assertEquals("Patient diagnosed with flu.", saved.getClinicalNotes());  // Fix here
    }

}
