package com.polis.hospitalmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity class representing a Clinical Record.
 */
@Setter
@Getter
@Entity
public class ClinicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relationship with Patient:
     * - Many clinical records can be associated with one patient.
     * - Uses `@ManyToOne` to define the foreign key relationship.
     * - `@JsonBackReference` prevents infinite recursion in JSON serialization.
     */
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference
    private Patient patient;

    /**
     * Stores the date when the clinical record was created.
     */
    private LocalDate recordDate;

    /**
     * Clinical notes containing medical observations, prescriptions, or test results.
     * - Uses `@Lob` to store large text content.
     */
    @Lob
    private String clinicalNotes;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getClinicalNotes() {
        return clinicalNotes;
    }

    public void setClinicalNotes(String clinicalNotes) {
        this.clinicalNotes = clinicalNotes;
    }


}
