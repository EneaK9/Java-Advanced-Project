package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.dto.AdmissionDTO;
import com.polis.hospitalmanagement.entity.Admission;
import com.polis.hospitalmanagement.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller responsible for handling patient admissions.
 */
@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    /**
     * Retrieves a list of all admissions.
     * @return List of all Admission entities.
     */
    @GetMapping
    public List<Admission> getAllAdmissions() {
        return admissionService.getAllAdmissions();
    }

    /**
     * Retrieves an admission by its ID.
     * @param id The ID of the admission.
     * @return ResponseEntity containing the Admission entity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(admissionService.getAdmissionById(id));
    }

    /**
     * Creates a new admission.
     * @param admissionDTO The AdmissionDTO containing admission details.
     * @return ResponseEntity containing the created AdmissionDTO.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdmissionDTO> createAdmission(@RequestBody AdmissionDTO admissionDTO) {
        Admission savedAdmission = admissionService.createAdmission(admissionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AdmissionDTO(
                savedAdmission.getId(),
                savedAdmission.getPatient().getId(),
                savedAdmission.getAdmissionDate(),
                savedAdmission.getNotes()
        ));
    }

    /**
     * Updates an existing admission.
     * @param id The ID of the admission to update.
     * @param admissionDTO The updated admission details.
     * @return ResponseEntity containing the updated AdmissionDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AdmissionDTO> updateAdmission(@PathVariable Long id, @RequestBody AdmissionDTO admissionDTO) {
        Admission updatedAdmission = admissionService.updateAdmission(id, admissionDTO);
        return ResponseEntity.ok(new AdmissionDTO(
                updatedAdmission.getId(),
                updatedAdmission.getPatient().getId(),
                updatedAdmission.getAdmissionDate(),
                updatedAdmission.getNotes()
        ));
    }

    /**
     * Deletes an admission by its ID.
     * @param id The ID of the admission to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmission(@PathVariable Long id) {
        admissionService.deleteAdmission(id);
        return ResponseEntity.noContent().build();
    }
}