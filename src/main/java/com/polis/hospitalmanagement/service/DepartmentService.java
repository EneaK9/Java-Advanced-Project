package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.dto.DepartmentDTO;
import com.polis.hospitalmanagement.entity.Department;
import com.polis.hospitalmanagement.exception.DepartmentNotFoundException;
import com.polis.hospitalmanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling department-related business logic.
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Retrieves a list of all departments.
     * @return List of Department entities.
     */
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll(); // Fetch all departments from the database
    }

    /**
     * Retrieves a department by its ID.
     * @param id The ID of the department.
     * @return The Department entity.
     * @throws RuntimeException if the department is not found.
     */
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    /**
     * Creates a new department based on the provided DTO.
     * @param departmentDTO Data transfer object containing department details.
     * @return The saved Department entity.
     * @throws RuntimeException if a department with the same name already exists.
     */
    public Department createDepartment(DepartmentDTO departmentDTO) {
        // Fetch the existing department or throw an exception if not found
        if (departmentRepository.existsByName(departmentDTO.getName())) {
            throw new RuntimeException("Department name already exists");
        }

        Department department = new Department(); // Create a new Department object
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());

        return departmentRepository.save(department);
    }

    /**
     * Updates an existing department.
     * @param id The ID of the department to update.
     * @param departmentDTO The updated department details.
     * @return The updated Department entity.
     * @throws RuntimeException if the department is not found.
     */
    public Department updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());

        return departmentRepository.save(department);
    }

    /**
     * Deletes a department by its ID.
     * @param id The ID of the department to delete.
     * @throws RuntimeException if the department has assigned patients.
     */
    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        if (!department.getPatients().isEmpty()) {
            throw new RuntimeException("Cannot delete department: Patients are assigned to this department.");
        }
        departmentRepository.delete(department); // Delete the department
    }

}

