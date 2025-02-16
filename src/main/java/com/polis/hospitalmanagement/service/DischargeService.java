package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.entity.Discharge;
import com.polis.hospitalmanagement.dto.DischargeDTO;
import com.polis.hospitalmanagement.entity.DischargeReason;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.repository.DischargeRepository;
import com.polis.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling discharge-related business logic.
 */
@Service
public class DischargeService {

    @Autowired
    private DischargeRepository dischargeRepository;

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Retrieves a list of all discharges.
     * @return List of Discharge entities.
     */
    public List<Discharge> getAllDischarges() {
        return dischargeRepository.findAll();
    }

    /**
     * Retrieves a discharge by its ID.
     * @param id The ID of the discharge.
     * @return The Discharge entity.
     * @throws RuntimeException if the discharge is not found.
     */
    public Discharge getDischargeById(Long id) {
        return dischargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discharge not found"));
    }

    /**
     * Creates a new discharge based on the provided DTO.
     * @param dischargeDTO Data transfer object containing discharge details.
     * @return The saved Discharge entity.
     * @throws RuntimeException if the patient is not found.
     */
    public Discharge createDischarge(DischargeDTO dischargeDTO) {
        Discharge discharge = new Discharge();  // Create a new discharge object
        discharge.setDischargeDate(dischargeDTO.getDischargeDate());
        discharge.setDischargeReason(dischargeDTO.getDischargeReasonEnum()); // Convert String to Enum
        discharge.setNotes(dischargeDTO.getNotes());

        // Ensure patient exists before assigning it to discharge
        Patient patient = patientRepository.findById(dischargeDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        discharge.setPatient(patient);

        return dischargeRepository.save(discharge);
    }

    /**
     * Updates an existing discharge.
     * @param id The ID of the discharge to update.
     * @param dischargeDTO The updated discharge details.
     * @return The updated Discharge entity.
     * @throws RuntimeException if the discharge or patient is not found.
     */
    public Discharge updateDischarge(Long id, DischargeDTO dischargeDTO) {
        // Fetch the existing discharge record
        Discharge discharge = dischargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discharge not found"));

        // Ensure the entity retains its ID
        discharge.setId(id);

        discharge.setDischargeDate(dischargeDTO.getDischargeDate());
        discharge.setDischargeReason(DischargeReason.valueOf(dischargeDTO.getDischargeReason())); // Convert String to Enum
        discharge.setNotes(dischargeDTO.getNotes());

        // Ensure patient exists before assigning it to discharge
        Patient patient = patientRepository.findById(dischargeDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        discharge.setPatient(patient);

        return dischargeRepository.save(discharge);
    }

    /**
     * Deletes a discharge by its ID.
     * @param id The ID of the discharge to delete.
     */
    public void deleteDischarge(Long id) {
        dischargeRepository.deleteById(id);
    }
}
