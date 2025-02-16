package com.polis.hospitalmanagement.repository;

import com.polis.hospitalmanagement.entity.Discharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Discharge entities.
 * It extends JpaRepository to provide built-in CRUD operations.
 */
@Repository // Marks this interface as a Spring Data Repository
public interface DischargeRepository extends JpaRepository<Discharge, Long> {

}
