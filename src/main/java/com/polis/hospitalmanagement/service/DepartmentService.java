package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.dto.DepartmentDTO;
import com.polis.hospitalmanagement.entity.Department;
import com.polis.hospitalmanagement.exception.DepartmentNotFoundException;
import com.polis.hospitalmanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department createDepartment(DepartmentDTO departmentDTO) {
        if (departmentRepository.existsByName(departmentDTO.getName())) {
            throw new RuntimeException("Department name already exists");
        }

        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());

        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());

        return departmentRepository.save(department);
    }


    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        if (!department.getPatients().isEmpty()) {
            throw new RuntimeException("Cannot delete department: Patients are assigned to this department.");
        }
        departmentRepository.delete(department);
    }

}

