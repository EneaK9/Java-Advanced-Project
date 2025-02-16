package com.polis.hospitalmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity class representing a Patient's Discharge.
 */
@Entity // Marks this class as a JPA entity (a table in the database)
@Table(name = "discharge")
public class Discharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Stores the date when the patient was discharged.
     */
    @Column(name = "discharge_date", nullable = false)
    private LocalDate dischargeDate;

    /**
     * Enum representing the possible reasons for discharge.
     * - HEALTHY → The patient recovered.
     * - TRANSFERRED → The patient was moved to another hospital.
     * - DECEASED → The patient passed away.
     */
    @Enumerated(EnumType.STRING) // Store Enum as a String in the database
    @Column(name = "reason", nullable = false)
    private DischargeReason dischargeReason; // ENUM: DECEASED, HEALTHY, TRANSFERRED

    /**
     * Relationship with Patient:
     * - Many discharges can be associated with one patient.
     * - Uses `@ManyToOne` to define the foreign key relationship.
     * - `@JsonBackReference` prevents infinite recursion in JSON serialization.
     */
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference
    private Patient patient;

    /**
     * Notes about the patient's discharge, written by medical staff.
     * - Uses `@Column(columnDefinition = "LONGTEXT")` to store large text content.
     */
    @Column(columnDefinition = "LONGTEXT")  // Allows for long text storage
    private String notes;

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for dischargeDate
    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    // Getter and Setter for reason
    public DischargeReason getDischargeReason() { return dischargeReason; }

    public void setDischargeReason(DischargeReason dischargeReason) { this.dischargeReason = dischargeReason; }

    // Getter and Setter for patient
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // Getter and Setter for notes
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

