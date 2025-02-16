package com.polis.hospitalmanagement.repository;

import com.polis.hospitalmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Department entities.
 * It extends JpaRepository to provide built-in CRUD operations.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    /**
     * Checks if a department with the given name exists in the database.
     * @param name The name of the department.
     * @return true if a department with the given name exists, false otherwise.
     */
    boolean existsByName(String name);
}

