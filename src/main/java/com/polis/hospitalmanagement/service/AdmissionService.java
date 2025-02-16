package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.dto.AdmissionDTO;
import com.polis.hospitalmanagement.entity.Admission;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.exception.AdmissionNotFoundException;
import com.polis.hospitalmanagement.repository.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polis.hospitalmanagement.repository.PatientRepository;

import java.util.List;

/**
 * Service class for handling admission-related business logic.
 */
@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Retrieves a list of all admissions.
     * @return List of Admission entities.
     */
    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll(); // Fetch all admissions from the database
    }

    /**
     * Retrieves an admission by its ID.
     * @param id The ID of the admission.
     * @return The Admission entity.
     * @throws RuntimeException if the admission is not found.
     */
    public Admission getAdmissionById(Long id) {
        return admissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Admission not found"));
    }

    /**
     * Creates a new admission based on the provided DTO.
     * @param admissionDTO Data transfer object containing admission details.
     * @return The saved Admission entity.
     * @throws RuntimeException if the patient is not found.
     */
    public Admission createAdmission(AdmissionDTO admissionDTO) {
        Patient patient = patientRepository.findById(admissionDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Admission admission = new Admission();  // Create a new admission object
        admission.setPatient(patient);
        admission.setAdmissionDate(admissionDTO.getAdmissionDate());
        admission.setNotes(admissionDTO.getNotes());

        return admissionRepository.save(admission);
    }

    /**
     * Updates an existing admission.
     * @param id The ID of the admission to update.
     * @param admissionDTO The updated admission details.
     * @return The updated Admission entity.
     * @throws RuntimeException if the admission or patient is not found.
     */
    public Admission updateAdmission(Long id, AdmissionDTO admissionDTO) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));

        Patient patient = patientRepository.findById(admissionDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        admission.setPatient(patient); // Update patient reference
        admission.setAdmissionDate(admissionDTO.getAdmissionDate());
        admission.setNotes(admissionDTO.getNotes());

        return admissionRepository.save(admission);
    }


    /**
     * Deletes an admission by its ID.
     * @param id The ID of the admission to delete.
     */
    public void deleteAdmission(Long id) {
        admissionRepository.deleteById(id);
    }

    /**
     * Saves an admission entity.
     * @param admission The admission entity to be saved.
     * @return The saved Admission entity.
     */
    public Admission saveAdmission(Admission admission) {
        return admissionRepository.save(admission);
    }

    public Admission findById(long id) {
        return admissionRepository.findById(id)
                .orElseThrow(() -> new AdmissionNotFoundException("Admission not found"));
    }
}
