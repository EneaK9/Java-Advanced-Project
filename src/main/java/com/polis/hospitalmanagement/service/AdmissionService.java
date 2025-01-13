package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.entity.Admission;
import com.polis.hospitalmanagement.repository.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    public Admission getAdmissionById(Long id) {
        return admissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Admission not found"));
    }

    public Admission createAdmission(Admission admission) {
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
}
