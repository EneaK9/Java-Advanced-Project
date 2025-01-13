package com.polis.hospitalmanagement.repository;


import com.polis.hospitalmanagement.entity.ClinicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalRecordRepository extends JpaRepository<ClinicalRecord, Long> {
}

