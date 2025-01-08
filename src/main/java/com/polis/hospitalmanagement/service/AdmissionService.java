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
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    public List<Admission> findAll() {
        return admissionRepository.findAll();
    }

    public Optional<Admission> findById(Long id) {
        return admissionRepository.findById(id);
    }

    public Admission save(Admission admission) {
        return admissionRepository.save(admission);
    }

    public void deleteById(Long id) {
        admissionRepository.deleteById(id);
    }
}
