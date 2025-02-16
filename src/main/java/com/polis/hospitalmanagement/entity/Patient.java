package com.polis.hospitalmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Patient.
 */
@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false)  // Ensures this column cannot be null
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String dateOfBirth;

    private String address;

    private String phone;

    /**
     * Relationship with Department:
     * - Many patients can belong to one department.
     * - Uses `@ManyToOne` to define the foreign key relationship.
     * - `@JsonBackReference` prevents infinite recursion in JSON serialization.
     */
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference
    private Department department;

    /**
     * Relationship with Admissions:
     * - One patient can have multiple admissions.
     * - Uses `@OneToMany` to define the one-to-many relationship.
     * - `CascadeType.ALL` means that if a patient is deleted, their admissions are also deleted.
     * - `orphanRemoval = true` ensures admissions without a patient are removed.
     * - `@JsonBackReference` prevents infinite loops during serialization.
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Admission> admissions = new ArrayList<>();

    /**
     * Relationship with Clinical Records:
     * - One patient can have multiple clinical records.
     * - Uses `@OneToMany` to define the one-to-many relationship.
     * - `CascadeType.ALL` means that if a patient is deleted, their clinical records are also deleted.
     * - `orphanRemoval = true` ensures orphaned records are removed.
     * - `@JsonBackReference` prevents infinite loops during serialization.
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ClinicalRecord> clinicalRecords = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment() {
        return department;
    }
}