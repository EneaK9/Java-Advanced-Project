package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.entity.Discharge;
import com.polis.hospitalmanagement.dto.DischargeDTO;
import com.polis.hospitalmanagement.entity.DischargeReason;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.repository.DischargeRepository;
import com.polis.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DischargeService {

    @Autowired
    private DischargeRepository dischargeRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Discharge> getAllDischarges() {
        return dischargeRepository.findAll();
    }

    public Discharge getDischargeById(Long id) {
        return dischargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discharge not found"));
    }

    public Discharge createDischarge(DischargeDTO dischargeDTO) {
        Discharge discharge = new Discharge();
        discharge.setDischargeDate(dischargeDTO.getDischargeDate());
        discharge.setDischargeReason(dischargeDTO.getDischargeReasonEnum()); // Convert String to Enum
        discharge.setNotes(dischargeDTO.getNotes());

        Patient patient = patientRepository.findById(dischargeDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        discharge.setPatient(patient);

        return dischargeRepository.save(discharge);
    }

    public Discharge updateDischarge(Long id, DischargeDTO dischargeDTO) {
        // Fetch the existing discharge record
        Discharge discharge = dischargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discharge not found"));

        // Ensure the entity retains its ID
        discharge.setId(id);

        discharge.setDischargeDate(dischargeDTO.getDischargeDate());
        discharge.setDischargeReason(DischargeReason.valueOf(dischargeDTO.getDischargeReason())); // Convert String to Enum
        discharge.setNotes(dischargeDTO.getNotes());

        // Ensure patient ID is valid
        Patient patient = patientRepository.findById(dischargeDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        discharge.setPatient(patient);

        return dischargeRepository.save(discharge);
    }



    public void deleteDischarge(Long id) {
        dischargeRepository.deleteById(id);
    }
}
