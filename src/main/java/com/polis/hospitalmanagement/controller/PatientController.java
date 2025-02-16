package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.dto.PatientDTO;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This controller handles all API requests related to patients.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired // Automatically injects the PatientService bean
    private PatientService patientService;
    /**
     * Retrieves a list of all patients.
     * @return List of PatientDTO objects.
     */
    @GetMapping
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return patients.stream().map(PatientDTO::new).collect(Collectors.toList());
    }

    /**
     * Retrieves a patient by their ID.
     * @param id The ID of the patient.
     * @return ResponseEntity containing the PatientDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(new PatientDTO(patient));  // Convert Entity to DTO
    }

    /**
     * Creates a new patient.
     * @param patientDTO The PatientDTO containing patient details.
     * @return ResponseEntity containing the created PatientDTO.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        Patient savedPatient = patientService.createPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PatientDTO(savedPatient));
    }

    /**
     * Updates an existing patient.
     * @param id The ID of the patient to update.
     * @param patientDTO The updated patient details.
     * @return ResponseEntity containing the updated PatientDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(
            @PathVariable Long id,
            @RequestBody PatientDTO patientDTO) {

        Patient updatedPatient = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(new PatientDTO(updatedPatient));
    }

    /**
     * Deletes a patient by their ID.
     * @param id The ID of the patient to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
