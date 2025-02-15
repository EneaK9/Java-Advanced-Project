package com.polis.hospitalmanagement.service;

import com.polis.hospitalmanagement.dto.PatientDTO;
import com.polis.hospitalmanagement.entity.Department;
import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.repository.DepartmentRepository;
import com.polis.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Patient createPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());

        // Convert String to LocalDate only if dateOfBirth is not null
        if (patientDTO.getDateOfBirth() != null) {
            patient.setDateOfBirth(String.valueOf(LocalDate.parse(patientDTO.getDateOfBirth())));
        }

        patient.setAddress(patientDTO.getAddress());
        patient.setPhone(patientDTO.getPhone());

        // Fetch the department from the database
        Department department = departmentRepository.findById(patientDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        patient.setDepartment(department);
        return patientRepository.save(patient);
    }


    public Patient updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setDateOfBirth(String.valueOf(LocalDate.parse(patientDTO.getDateOfBirth()))); // Ensure it's String -> LocalDate
        patient.setAddress(patientDTO.getAddress());
        patient.setPhone(patientDTO.getPhone());

        Department department = departmentRepository.findById(patientDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        patient.setDepartment(department);

        return patientRepository.save(patient);
    }


    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }
}
