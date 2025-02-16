package com.polis.hospitalmanagement.repository;

import com.polis.hospitalmanagement.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Admission entities.
 * It extends JpaRepository to provide built-in CRUD operations.
 */
public interface AdmissionRepository extends JpaRepository<Admission, Long> {

}
