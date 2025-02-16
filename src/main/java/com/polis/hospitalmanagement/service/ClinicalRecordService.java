package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.dto.ClinicalRecordDTO;
import com.polis.hospitalmanagement.entity.ClinicalRecord;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.exception.ClinicalRecordNotFoundException;
import com.polis.hospitalmanagement.repository.ClinicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.polis.hospitalmanagement.repository.PatientRepository;

import java.util.List;

/**
 * Service class for handling clinical record-related business logic.
 */
@Service // Marks this class as a Spring service component
public class ClinicalRecordService {

    @Autowired
    private ClinicalRecordRepository clinicalRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Retrieves a list of all clinical records.
     * @return List of ClinicalRecord entities.
     */
    public List<ClinicalRecord> getAllClinicalRecords() {
        return clinicalRecordRepository.findAll();
    }

    /**
     * Retrieves a clinical record by its ID.
     * @param id The ID of the clinical record.
     * @return The ClinicalRecord entity.
     * @throws RuntimeException if the clinical record is not found.
     */
    public ClinicalRecord getClinicalRecordById(Long id) {
        return clinicalRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinical record not found"));
    }

    /**
     * Creates a new clinical record based on the provided DTO.
     * @param clinicalRecordDTO Data transfer object containing clinical record details.
     * @return The saved ClinicalRecord entity.
     * @throws RuntimeException if the patient is not found.
     */
    public ClinicalRecord createClinicalRecord(ClinicalRecordDTO clinicalRecordDTO) {
        Patient patient = patientRepository.findById(clinicalRecordDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        ClinicalRecord clinicalRecord = new ClinicalRecord();
        clinicalRecord.setRecordDate(clinicalRecordDTO.getRecordDate());
        clinicalRecord.setClinicalNotes(clinicalRecordDTO.getClinicalNotes());
        clinicalRecord.setPatient(patient);

        return clinicalRecordRepository.save(clinicalRecord);
    }

    /**
     * Updates an existing clinical record.
     * @param id The ID of the clinical record to update.
     * @param clinicalRecordDTO The updated clinical record details.
     * @return The updated ClinicalRecord entity.
     * @throws RuntimeException if the clinical record is not found.
     */
    public ClinicalRecord updateClinicalRecord(Long id, ClinicalRecordDTO clinicalRecordDTO) {
        // Fetch the existing clinical record or throw an exception if not found
        ClinicalRecord clinicalRecord = clinicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinical record not found"));

        clinicalRecord.setRecordDate(clinicalRecordDTO.getRecordDate()); // Update record date
        clinicalRecord.setClinicalNotes(clinicalRecordDTO.getClinicalNotes());

        return clinicalRecordRepository.save(clinicalRecord);
    }

    /**
     * Deletes a clinical record by its ID.
     * @param id The ID of the clinical record to delete.
     */
    public void deleteClinicalRecord(Long id) {
        clinicalRecordRepository.deleteById(id);
    }

    /**
     * Saves a clinical record entity.
     * @param clinicalRecord The clinical record entity to be saved.
     * @return The saved ClinicalRecord entity.
     */
    public ClinicalRecord saveClinicalRecord(ClinicalRecord clinicalRecord) {
        return clinicalRecordRepository.save(clinicalRecord);
    }

    /**
     * Finds a clinical record by its ID.
     * @param id The ID of the clinical record.
     * @return The ClinicalRecord entity.
     * @throws ClinicalRecordNotFoundException if the clinical record is not found.
     */
    public ClinicalRecord findById(long id) {
        return clinicalRecordRepository.findById(id)
                .orElseThrow(() -> new ClinicalRecordNotFoundException("Clinical Record not found"));
    }
}
