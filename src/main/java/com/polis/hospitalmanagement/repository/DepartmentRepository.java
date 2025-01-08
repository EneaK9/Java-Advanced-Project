package com.polis.hospitalmanagement.repository;

import com.polis.hospitalmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Additional custom query methods can be defined here
}
