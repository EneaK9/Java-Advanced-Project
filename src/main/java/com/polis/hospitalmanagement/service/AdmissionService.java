package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.dto.AdmissionDTO;
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

    public Admission createAdmission(AdmissionDTO admissionDTO) {
        Patient patient = patientRepository.findById(admissionDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Admission admission = new Admission();
        admission.setPatient(patient);
        admission.setAdmissionDate(admissionDTO.getAdmissionDate());
        admission.setNotes(admissionDTO.getNotes());

        return admissionRepository.save(admission);
    }

    public Admission updateAdmission(Long id, AdmissionDTO admissionDTO) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));

        Patient patient = patientRepository.findById(admissionDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        admission.setPatient(patient);
        admission.setAdmissionDate(admissionDTO.getAdmissionDate());
        admission.setNotes(admissionDTO.getNotes());

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
