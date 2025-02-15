package com.polis.hospitalmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdmissionDTO {
    private Long id;
    private Long patientId;
    private LocalDate admissionDate;
    private String notes;

    public AdmissionDTO() {}

    public AdmissionDTO(Long id, Long patientId, LocalDate admissionDate, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.notes = notes;
    }
}
