package com.polis.hospitalmanagement.dto;
import com.polis.hospitalmanagement.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@NoArgsConstructor // Needed for Jackson deserialization
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String dateOfBirth;
    private Long departmentId;  // Reference to department instead of full object

    // Constructor to map Patient entity to DTO
    public PatientDTO(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        this.id = patient.getId();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.address = patient.getAddress();
        this.phone = patient.getPhone();
        this.dateOfBirth = patient.getDateOfBirth();
        this.departmentId = (patient.getDepartment() != null) ? patient.getDepartment().getId() : null;
    }

    // Getters
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getDateOfBirth() { return dateOfBirth; }
    public Long getDepartmentId() { return departmentId; }
}
