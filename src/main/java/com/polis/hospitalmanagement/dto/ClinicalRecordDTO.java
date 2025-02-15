package com.polis.hospitalmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClinicalRecordDTO {
    private Long id;
    private LocalDate recordDate;
    private String clinicalNotes;
    private Long patientId; // Reference to the patient

    public ClinicalRecordDTO() {}

    public ClinicalRecordDTO(Long id, LocalDate recordDate, String clinicalNotes, Long patientId) {
        this.id = id;
        this.recordDate = recordDate;
        this.clinicalNotes = clinicalNotes;
        this.patientId = patientId;
    }
}
