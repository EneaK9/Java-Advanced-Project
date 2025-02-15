package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.entity.Discharge;
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

    public Discharge createDischarge(Long patientId, LocalDateTime dischargeDate, String dischargeReason, String notes) {
        // Fetch the Patient object from the database
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Create and set the Discharge object
        Discharge discharge = new Discharge();
        discharge.setPatient(patient); // ✅ Ensure patient exists
        discharge.setDischargeDate(dischargeDate);
        discharge.setReason(DischargeReason.valueOf(dischargeReason));
        discharge.setNotes(notes);

        return dischargeRepository.save(discharge);
    }

    public Discharge updateDischarge(Long id, LocalDateTime dischargeDate, String dischargeReason, String notes) {
        Discharge discharge = dischargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discharge record not found"));

        discharge.setDischargeDate(dischargeDate);
        discharge.setReason(DischargeReason.valueOf(dischargeReason));
        discharge.setNotes(notes);

        return dischargeRepository.save(discharge);
    }


    public void deleteDischarge(Long id) {
        dischargeRepository.deleteById(id);
    }
}
