package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.dto.DischargeDTO;
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
    public ResponseEntity<DischargeDTO> createDischarge(@RequestBody DischargeDTO dischargeDTO) {
        Discharge savedDischarge = dischargeService.createDischarge(dischargeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new DischargeDTO(
                        savedDischarge.getId(),
                        savedDischarge.getDischargeDate(),
                        savedDischarge.getDischargeReason().name(), // Convert Enum to String
                        savedDischarge.getNotes(),
                        savedDischarge.getPatient().getId()
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DischargeDTO> updateDischarge(@PathVariable Long id, @RequestBody DischargeDTO dischargeDTO) {
        Discharge updatedDischarge = dischargeService.updateDischarge(id, dischargeDTO);
        return ResponseEntity.ok(new DischargeDTO(
                updatedDischarge.getId(),
                updatedDischarge.getDischargeDate(),
                updatedDischarge.getDischargeReason().name(), // Convert Enum to String
                updatedDischarge.getNotes(),
                updatedDischarge.getPatient().getId()
        ));
    }

    @DeleteMapping("/{id}")
    public void deleteDischarge(@PathVariable Long id) {
        dischargeService.deleteDischarge(id);
    }
}
