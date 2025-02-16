package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.dto.DischargeDTO;
import com.polis.hospitalmanagement.entity.Discharge;
import com.polis.hospitalmanagement.service.DischargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling patient discharges.
 */
@RestController
@RequestMapping("/api/discharges")
public class DischargeController {

    @Autowired  // Automatically injects the DischargeService
    private DischargeService dischargeService;

    /**
     * Retrieves a list of all discharges.
     * @return List of all Discharge entities.
     */
    @GetMapping
    public List<Discharge> getAllDischarges() {
        return dischargeService.getAllDischarges();
    }

    /**
     * Retrieves a discharge by its ID.
     * @param id The ID of the discharge.
     * @return The Discharge entity.
     */
    @GetMapping("/{id}")
    public Discharge getDischargeById(@PathVariable Long id) {
        return dischargeService.getDischargeById(id); // Return the discharge if found
    }

    /**
     * Creates a new discharge.
     * @param dischargeDTO The DischargeDTO containing discharge details.
     * @return ResponseEntity containing the created DischargeDTO.
     */
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

    /**
     * Updates an existing discharge.
     * @param id The ID of the discharge to update.
     * @param dischargeDTO The updated discharge details.
     * @return ResponseEntity containing the updated DischargeDTO.
     */
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

    /**
     * Deletes a discharge by its ID.
     * @param id The ID of the discharge to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteDischarge(@PathVariable Long id) {
        dischargeService.deleteDischarge(id);
    }
}
