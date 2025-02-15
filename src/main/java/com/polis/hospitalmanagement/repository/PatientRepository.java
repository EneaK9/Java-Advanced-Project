package com.polis.hospitalmanagement.repository;

import com.polis.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Additional custom query methods can be defined here
}
