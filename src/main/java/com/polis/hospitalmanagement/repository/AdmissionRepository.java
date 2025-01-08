package com.polis.hospitalmanagement.repository;

import com.polis.hospitalmanagement.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    // Additional custom query methods can be defined here
}
