package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.entity.ClinicalRecord;
import com.polis.hospitalmanagement.repository.ClinicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ClinicalRecordService {

    @Autowired
    private ClinicalRecordRepository clinicalRecordRepository;

    public List<ClinicalRecord> getAllClinicalRecords() {
        return clinicalRecordRepository.findAll();
    }

    public ClinicalRecord getClinicalRecordById(Long id) {
        return clinicalRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinical record not found"));
    }

    public ClinicalRecord createClinicalRecord(ClinicalRecord clinicalRecord) {
        return clinicalRecordRepository.save(clinicalRecord);
    }

    public ClinicalRecord updateClinicalRecord(Long id, ClinicalRecord recordDetails) {
        ClinicalRecord record = getClinicalRecordById(id);
        record.setRecordDate(recordDetails.getRecordDate());
        record.setClinicalNotes(recordDetails.getClinicalNotes());
        return clinicalRecordRepository.save(record);
    }

    public void deleteClinicalRecord(Long id) {
        clinicalRecordRepository.deleteById(id);
    }
}
