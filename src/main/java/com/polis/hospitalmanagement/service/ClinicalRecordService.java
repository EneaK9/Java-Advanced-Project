package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.entity.ClinicalRecord;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.exception.ClinicalRecordNotFoundException;
import com.polis.hospitalmanagement.repository.ClinicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.polis.hospitalmanagement.repository.PatientRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClinicalRecordService {

    @Autowired
    private ClinicalRecordRepository clinicalRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<ClinicalRecord> getAllClinicalRecords() {
        return clinicalRecordRepository.findAll();
    }

    public ClinicalRecord getClinicalRecordById(Long id) {
        return clinicalRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinical record not found"));
    }

    public ClinicalRecord createClinicalRecord(Long patientId, LocalDate recordDate, String clinicalNotes) {
        // Fetch the Patient object from the database
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Create and set the Clinical Record object
        ClinicalRecord clinicalRecord = new ClinicalRecord();
        clinicalRecord.setPatient(patient); // ✅ Ensure patient exists
        clinicalRecord.setRecordDate(recordDate);
        clinicalRecord.setClinicalNotes(clinicalNotes);

        return clinicalRecordRepository.save(clinicalRecord);
    }

    public ClinicalRecord updateClinicalRecord(Long id, LocalDate recordDate, String clinicalNotes) {
        ClinicalRecord clinicalRecord = clinicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinical Record not found"));

        clinicalRecord.setRecordDate(recordDate);
        clinicalRecord.setClinicalNotes(clinicalNotes);

        return clinicalRecordRepository.save(clinicalRecord);
    }


    public void deleteClinicalRecord(Long id) {
        clinicalRecordRepository.deleteById(id);
    }

    public ClinicalRecord saveClinicalRecord(ClinicalRecord clinicalRecord) {
        return clinicalRecordRepository.save(clinicalRecord);
    }

    public ClinicalRecord findById(long id) {
        return clinicalRecordRepository.findById(id)
                .orElseThrow(() -> new ClinicalRecordNotFoundException("Clinical Record not found"));
    }
}
