package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.entity.Admission;
import com.polis.hospitalmanagement.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @GetMapping
    public List<Admission> getAllAdmissions() {
        return admissionService.getAllAdmissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(admissionService.getAdmissionById(id));
    }

    @PostMapping
    public ResponseEntity<Admission> createAdmission(@RequestBody Map<String, Object> request) {
        Long patientId = Long.valueOf(request.get("patientId").toString());
        LocalDate admissionDate = LocalDate.parse(request.get("admissionDate").toString());
        String notes = (String) request.get("notes");

        Admission admission = admissionService.createAdmission(patientId, admissionDate, notes);
        return ResponseEntity.status(HttpStatus.CREATED).body(admission);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Admission> updateAdmission(@PathVariable Long id, @RequestBody Admission admission) {
        return ResponseEntity.ok(admissionService.updateAdmission(id, admission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmission(@PathVariable Long id) {
        admissionService.deleteAdmission(id);
        return ResponseEntity.noContent().build();
    }
}