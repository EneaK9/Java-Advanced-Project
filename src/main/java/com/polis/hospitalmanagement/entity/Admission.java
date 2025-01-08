package com.polis.hospitalmanagement.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admission")
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private String admissionReason;

    @Column(nullable = true)
    private String dischargeReason;

    // Constructors, getters, setters
    public Admission() {}

    public Admission(Patient patient, String admissionReason) {
        this.patient = patient;
        this.admissionReason = admissionReason;
    }

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

    public String getAdmissionReason() {
        return admissionReason;
    }

    public void setAdmissionReason(String admissionReason) {
        this.admissionReason = admissionReason;
    }

    public String getDischargeReason() {
        return dischargeReason;
    }

    public void setDischargeReason(String dischargeReason) {
        this.dischargeReason = dischargeReason;
    }
}
