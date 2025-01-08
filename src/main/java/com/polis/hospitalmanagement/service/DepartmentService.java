package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.entity.Department;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.entity.Admission;
import com.polis.hospitalmanagement.repository.DepartmentRepository;
import com.polis.hospitalmanagement.repository.PatientRepository;
import com.polis.hospitalmanagement.repository.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
