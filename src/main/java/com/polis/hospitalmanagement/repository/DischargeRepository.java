package com.polis.hospitalmanagement.repository;

import com.polis.hospitalmanagement.entity.Discharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DischargeRepository extends JpaRepository<Discharge, Long> {
}
