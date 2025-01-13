package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.entity.ClinicalRecord;
import com.polis.hospitalmanagement.service.ClinicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<ClinicalRecord> createClinicalRecord(@RequestBody ClinicalRecord clinicalRecord) {
        return ResponseEntity.ok(clinicalRecordService.createClinicalRecord(clinicalRecord));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalRecord> updateClinicalRecord(@PathVariable Long id, @RequestBody ClinicalRecord clinicalRecord) {
        return ResponseEntity.ok(clinicalRecordService.updateClinicalRecord(id, clinicalRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicalRecord(@PathVariable Long id) {
        clinicalRecordService.deleteClinicalRecord(id);
        return ResponseEntity.noContent().build();
    }
}
