package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.entity.Admission;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.exception.AdmissionNotFoundException;
import com.polis.hospitalmanagement.repository.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polis.hospitalmanagement.repository.PatientRepository;


import java.time.LocalDate;
import java.util.List;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    public Admission getAdmissionById(Long id) {
        return admissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Admission not found"));
    }

    public Admission createAdmission(Long patientId, LocalDate admissionDate, String notes) {
        // Fetch the Patient object from the database
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Create and set the Admission object
        Admission admission = new Admission();
        admission.setPatient(patient); // ✅ Assign the actual Patient object
        admission.setAdmissionDate(admissionDate);
        admission.setNotes(notes);

        return admissionRepository.save(admission);
    }

    public Admission updateAdmission(Long id, Admission admissionDetails) {
        Admission admission = getAdmissionById(id);
        admission.setAdmissionDate(admissionDetails.getAdmissionDate());
        admission.setDischargeDate(admissionDetails.getDischargeDate());
        admission.setDischargeReason(admissionDetails.getDischargeReason());
        admission.setNotes(admissionDetails.getNotes());
        return admissionRepository.save(admission);
    }

    public void deleteAdmission(Long id) {
        admissionRepository.deleteById(id);
    }

    public Admission saveAdmission(Admission admission) {
        return admissionRepository.save(admission);
    }

    public Admission findById(long id) {
        return admissionRepository.findById(id)
                .orElseThrow(() -> new AdmissionNotFoundException("Admission not found"));
    }
}
