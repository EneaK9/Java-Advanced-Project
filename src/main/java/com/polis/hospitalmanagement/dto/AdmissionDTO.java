package com.polis.hospitalmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO class for transferring admission data.
 * It simplifies data transfer by excluding unnecessary fields.
 */
@Getter
@Setter
public class AdmissionDTO {
    private Long id;
    private Long patientId;
    private LocalDate admissionDate;
    private String notes;

    /**
     * Default constructor (required for Jackson serialization).
     */
    public AdmissionDTO() {}

    /**
     * Constructor to initialize AdmissionDTO fields.
     * @param id The ID of the admission.
     * @param patientId The ID of the associated patient.
     * @param admissionDate The date of admission.
     * @param notes Additional notes regarding the admission.
     */
    public AdmissionDTO(Long id, Long patientId, LocalDate  admissionDate, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.notes = notes;
    }
    public Long getPatientId() {
        return patientId;
    }

    public LocalDate getAdmissionDate() {  // Ensure this matches the field type
        return admissionDate;
    }

    public String getNotes() {
        return notes;
    }
}
