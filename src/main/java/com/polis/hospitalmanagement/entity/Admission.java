package com.polis.hospitalmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Entity class representing a Patient's Admission.
 */
@Setter
@Getter
@Entity  // Marks this class as a JPA entity (a table in the database)
@Table(name = "admission")
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relationship with Patient:
     * - Many admissions can be associated with one patient.
     * - Uses `@ManyToOne` to define the foreign key relationship.
     * - `@JsonBackReference` prevents infinite recursion in JSON serialization.
     */
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference
    private Patient patient;

    private LocalDate admissionDate;

    private LocalDate dischargeDate;

    /**
     * Enum representing the possible reasons for discharge.
     * - HEALTHY → The patient recovered.
     * - TRANSFERRED → The patient was moved to another hospital.
     * - DECEASED → The patient passed away.
     */
    @Enumerated(EnumType.STRING)
    private DischargeReason dischargeReason;

    /**
     * Notes about the patient's admission, written by medical staff.
     * - Uses `@Lob` to store large text content.
     */
    @Lob
    private String notes;

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

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public DischargeReason getDischargeReason() {
        return dischargeReason;
    }

    public void setDischargeReason(DischargeReason dischargeReason) {
        this.dischargeReason = dischargeReason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public enum DischargeReason {
        HEALTHY, TRANSFERRED, DECEASED
    }

}
