package com.polis.hospitalmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entity class representing a hospital department.
 */
@Setter
@Getter
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the department.
     * - Must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true) // Ensures name is unique and required
    private String name;

    /**
     * Description of the department.
     * - Optional field for additional details.
     */
    private String description;

    /**
     * Relationship with Patients:
     * - A department can have multiple patients.
     * - Uses `@OneToMany` to define the one-to-many relationship.
     * - `CascadeType.ALL` ensures that if a department is deleted, all associated patients are also deleted.
     * - `orphanRemoval = true` ensures that any patient without a department is removed.
     * - `@JsonIgnore` prevents infinite recursion when serializing JSON.
     */
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // ✅ Prevents infinite recursion
    private List<Patient> patients;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

}
