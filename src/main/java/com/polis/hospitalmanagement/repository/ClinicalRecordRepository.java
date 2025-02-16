package com.polis.hospitalmanagement.repository;


import com.polis.hospitalmanagement.entity.ClinicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing ClinicalRecord entities.
 * It extends JpaRepository to provide built-in CRUD operations.
 */
public interface ClinicalRecordRepository extends JpaRepository<ClinicalRecord, Long> {

}

