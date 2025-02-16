package com.polis.hospitalmanagement.repository;

import com.polis.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Patient entities.
 * It extends JpaRepository to provide built-in CRUD operations.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
