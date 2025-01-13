package com.polis.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "patient")
public class Patient {

    // Getters and Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Setter
    @Getter
    @Column(nullable = false)
    private String lastName;

    @Setter
    @Getter
    @Column(nullable = false)
    private String dateOfBirth;

    @Setter

    @Getter
    private String address;

    @Setter
    @Getter

    private String phone;

    @Getter
    @Setter

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;


    @Setter
    @Getter
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Admission> admissions = new ArrayList<>();

    @Setter

    @Getter
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClinicalRecord> clinicalRecords;

    // Getters and Setters


    public String getFirstName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.name = firstName;
    }

}