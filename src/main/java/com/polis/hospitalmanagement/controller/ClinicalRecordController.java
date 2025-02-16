package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.dto.ClinicalRecordDTO;
import com.polis.hospitalmanagement.entity.ClinicalRecord;
import com.polis.hospitalmanagement.service.ClinicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling clinical records.
 */
@RestController
@RequestMapping("/api/clinical-records")
public class ClinicalRecordController {

    @Autowired
    private ClinicalRecordService clinicalRecordService;

    /**
     * Retrieves a list of all clinical records.
     * @return List of all ClinicalRecord entities.
     */
    @GetMapping
    public List<ClinicalRecord> getAllClinicalRecords() {
        return clinicalRecordService.getAllClinicalRecords();
    }

    /**
     * Retrieves a clinical record by its ID.
     * @param id The ID of the clinical record.
     * @return ResponseEntity containing the ClinicalRecord entity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClinicalRecord> getClinicalRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(clinicalRecordService.getClinicalRecordById(id));
    }

    /**
     * Creates a new clinical record.
     * @param clinicalRecordDTO The ClinicalRecordDTO containing clinical record details.
     * @return ResponseEntity containing the created ClinicalRecordDTO.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicalRecordDTO> createClinicalRecord(@RequestBody ClinicalRecordDTO clinicalRecordDTO) {
        ClinicalRecord savedRecord = clinicalRecordService.createClinicalRecord(clinicalRecordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClinicalRecordDTO(
                savedRecord.getId(),
                savedRecord.getRecordDate(),
                savedRecord.getClinicalNotes(),
                savedRecord.getPatient().getId()
        ));
    }

    /**
     * Updates an existing clinical record.
     * @param id The ID of the clinical record to update.
     * @param clinicalRecordDTO The updated clinical record details.
     * @return ResponseEntity containing the updated ClinicalRecordDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClinicalRecordDTO> updateClinicalRecord(@PathVariable Long id, @RequestBody ClinicalRecordDTO clinicalRecordDTO) {
        ClinicalRecord updatedRecord = clinicalRecordService.updateClinicalRecord(id, clinicalRecordDTO);
        return ResponseEntity.ok(new ClinicalRecordDTO(
                updatedRecord.getId(),
                updatedRecord.getRecordDate(),
                updatedRecord.getClinicalNotes(),
                updatedRecord.getPatient().getId()
        ));
    }

    /**
     * Deletes a clinical record by its ID.
     * @param id The ID of the clinical record to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicalRecord(@PathVariable Long id) {
        clinicalRecordService.deleteClinicalRecord(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content response
    }
}
