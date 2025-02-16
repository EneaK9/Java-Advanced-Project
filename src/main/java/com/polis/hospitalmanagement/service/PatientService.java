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
/**
 * Service class for handling patient-related business logic.
 */
@Service
public class PatientService {

    @Autowired  // Automatically injects the PatientRepository
    private PatientRepository patientRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    /**
     * Retrieves a list of all patients from the database.
     * @return List of Patient entities.
     */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    /**
     * Retrieves a single patient by their ID.
     * @param id The ID of the patient.
     * @return The Patient entity.
     * @throws RuntimeException if the patient is not found.
     */
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
    /**
     * Creates a new patient based on the provided DTO.
     * @param patientDTO Data transfer object containing patient details.
     * @return The saved Patient entity.
     * @throws RuntimeException if the associated department is not found.
     */
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

        patient.setDepartment(department);   // Assign the department to the patient
        return patientRepository.save(patient);
    }

    /**
     * Updates an existing patient.
     * @param id The ID of the patient to update.
     * @param patientDTO The updated patient details.
     * @return The updated Patient entity.
     * @throws RuntimeException if the patient or department is not found.
     */
    public Patient updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Update patient details
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setDateOfBirth(String.valueOf(LocalDate.parse(patientDTO.getDateOfBirth()))); // Ensure it's String -> LocalDate
        patient.setAddress(patientDTO.getAddress());
        patient.setPhone(patientDTO.getPhone());

        // Retrieve and assign the new department
        Department department = departmentRepository.findById(patientDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        patient.setDepartment(department);

        return patientRepository.save(patient);
    }

    /**
     * Deletes a patient by their ID.
     * @param id The ID of the patient to delete.
     */
    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }
}
