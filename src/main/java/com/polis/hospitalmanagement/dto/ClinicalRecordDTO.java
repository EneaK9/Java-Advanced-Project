package com.polis.hospitalmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO class for transferring clinical record data.
 * It simplifies data transfer by excluding unnecessary fields.
 */
@Getter
@Setter
public class ClinicalRecordDTO {
    private Long id;
    private LocalDate recordDate;
    private String clinicalNotes;
    private Long patientId; // Reference to the patient associated with this clinical record

    /**
     * Default constructor (required for Jackson serialization).
     */
    public ClinicalRecordDTO() {}

    /**
     * Constructor to initialize ClinicalRecordDTO fields.
     * @param id The ID of the clinical record.
     * @param recordDate The date when the clinical record was created.
     * @param clinicalNotes Additional medical notes about the patient's condition.
     * @param patientId The ID of the patient associated with this record.
     */
    public ClinicalRecordDTO(Long id, LocalDate recordDate, String clinicalNotes, Long patientId) {
        this.id = id;
        this.recordDate = recordDate;
        this.clinicalNotes = clinicalNotes;
        this.patientId = patientId;
    }
    public Long getPatientId() {
        return patientId;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public String getClinicalNotes() {
        return clinicalNotes;
    }
}
