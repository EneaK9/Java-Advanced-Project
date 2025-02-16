package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.dto.DepartmentDTO;
import com.polis.hospitalmanagement.entity.Department;
import com.polis.hospitalmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling departments.
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * Retrieves a list of all departments.
     * @return List of all Department entities.
     */
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    /**
     * Retrieves a department by its ID.
     * @param id The ID of the department.
     * @return ResponseEntity containing the Department entity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    /**
     * Creates a new department.
     * @param departmentDTO The DepartmentDTO containing department details.
     * @return ResponseEntity containing the created DepartmentDTO.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department savedDepartment = departmentService.createDepartment(departmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DepartmentDTO(
                savedDepartment.getId(),
                savedDepartment.getName(),
                savedDepartment.getDescription()
        ));
    }

    /**
     * Updates an existing department.
     * @param id The ID of the department to update.
     * @param departmentDTO The updated department details.
     * @return ResponseEntity containing the updated DepartmentDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        Department updatedDepartment = departmentService.updateDepartment(id, departmentDTO);
        return ResponseEntity.ok(new DepartmentDTO(
                updatedDepartment.getId(),
                updatedDepartment.getName(),
                updatedDepartment.getDescription()
        ));
    }

    /**
     * Deletes a department by its ID.
     * @param id The ID of the department to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);   // Call service to delete department
        return ResponseEntity.noContent().build(); // Return 204 No Content response
    }
}