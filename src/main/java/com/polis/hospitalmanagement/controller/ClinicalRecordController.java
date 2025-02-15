package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.dto.ClinicalRecordDTO;
import com.polis.hospitalmanagement.entity.ClinicalRecord;
import com.polis.hospitalmanagement.service.ClinicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clinical-records")
public class ClinicalRecordController {

    @Autowired
    private ClinicalRecordService clinicalRecordService;

    @GetMapping
    public List<ClinicalRecord> getAllClinicalRecords() {
        return clinicalRecordService.getAllClinicalRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalRecord> getClinicalRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(clinicalRecordService.getClinicalRecordById(id));
    }

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


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicalRecord(@PathVariable Long id) {
        clinicalRecordService.deleteClinicalRecord(id);
        return ResponseEntity.noContent().build();
    }
}
