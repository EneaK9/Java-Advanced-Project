package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.entity.Discharge;
import com.polis.hospitalmanagement.service.DischargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/discharges")
public class DischargeController {

    @Autowired
    private DischargeService dischargeService;

    @GetMapping
    public List<Discharge> getAllDischarges() {
        return dischargeService.getAllDischarges();
    }

    @GetMapping("/{id}")
    public Discharge getDischargeById(@PathVariable Long id) {
        return dischargeService.getDischargeById(id);
    }

    @PostMapping
    public ResponseEntity<Discharge> createDischarge(@RequestBody Map<String, Object> request) {
        Long patientId = Long.valueOf(request.get("patientId").toString());
        LocalDateTime dischargeDate = LocalDateTime.parse(request.get("dischargeDate").toString());
        String dischargeReason = request.get("dischargeReason").toString();
        String notes = request.get("notes").toString();

        Discharge discharge = dischargeService.createDischarge(patientId, dischargeDate, dischargeReason, notes);
        return ResponseEntity.status(HttpStatus.CREATED).body(discharge);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Discharge> updateDischarge(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        LocalDateTime dischargeDate = LocalDateTime.parse(request.get("dischargeDate").toString());
        String dischargeReason = request.get("dischargeReason").toString();
        String notes = request.get("notes").toString();

        Discharge updatedDischarge = dischargeService.updateDischarge(id, dischargeDate, dischargeReason, notes);
        return ResponseEntity.ok(updatedDischarge);
    }


    @DeleteMapping("/{id}")
    public void deleteDischarge(@PathVariable Long id) {
        dischargeService.deleteDischarge(id);
    }
}
