package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.entity.Admission;
import com.polis.hospitalmanagement.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @GetMapping
    public List<Admission> getAllAdmissions() {
        return admissionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable Long id) {
        Optional<Admission> admission = admissionService.findById(id);
        return admission.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Admission createAdmission(@RequestBody Admission admission) {
        return admissionService.save(admission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmission(@PathVariable Long id) {
        admissionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
