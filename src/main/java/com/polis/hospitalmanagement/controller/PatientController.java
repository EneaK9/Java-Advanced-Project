package com.polis.hospitalmanagement.controller;

import com.polis.hospitalmanagement.entity.Patient;
import com.polis.hospitalmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Map<String, Object> request) {
        Patient patient = new Patient();
        patient.setFirstName((String) request.get("firstName"));
        patient.setLastName((String) request.get("lastName"));
        patient.setDateOfBirth((String) request.get("dateOfBirth"));
        patient.setAddress((String) request.get("address"));
        patient.setPhone((String) request.get("phone"));

        Long departmentId = Long.valueOf(request.get("departmentId").toString());
        Patient savedPatient = patientService.createPatient(patient, departmentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        String firstName = request.get("firstName").toString();
        String lastName = request.get("lastName").toString();
        String dateOfBirth = request.get("dateOfBirth").toString();
        String address = request.get("address").toString();
        String phone = request.get("phone").toString();
        Long departmentId = Long.valueOf(request.get("departmentId").toString());

        Patient updatedPatient = patientService.updatePatient(id, firstName, lastName, dateOfBirth, address, phone, departmentId);
        return ResponseEntity.ok(updatedPatient);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
