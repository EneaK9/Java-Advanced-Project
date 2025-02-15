package com.polis.hospitalmanagement.controller;

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

    @PostMapping
    public ResponseEntity<ClinicalRecord> createClinicalRecord(@RequestBody Map<String, Object> request) {
        Long patientId = Long.valueOf(request.get("patientId").toString());
        LocalDate recordDate = LocalDate.parse(request.get("recordDate").toString());
        String clinicalNotes = (String) request.get("clinicalNotes");

        ClinicalRecord clinicalRecord = clinicalRecordService.createClinicalRecord(patientId, recordDate, clinicalNotes);
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicalRecord);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicalRecord> updateClinicalRecord(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        LocalDate recordDate = LocalDate.parse(request.get("recordDate").toString());
        String clinicalNotes = request.get("clinicalNotes").toString();

        ClinicalRecord updatedRecord = clinicalRecordService.updateClinicalRecord(id, recordDate, clinicalNotes);
        return ResponseEntity.ok(updatedRecord);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicalRecord(@PathVariable Long id) {
        clinicalRecordService.deleteClinicalRecord(id);
        return ResponseEntity.noContent().build();
    }
}
