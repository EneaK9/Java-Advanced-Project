package com.polis.hospitalmanagement.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO class for transferring department data.
 * It simplifies data transfer by excluding unnecessary fields.
 */
@Getter
@Setter
public class DepartmentDTO {
    private Long id;
    private String name;
    private String description;

    /**
     * Default constructor (required for Jackson serialization).
     */
    public DepartmentDTO() {}

    /**
     * Constructor to initialize DepartmentDTO fields.
     * @param id The ID of the department.
     * @param name The name of the department.
     * @param description A description of the department's function or purpose.
     */
    public DepartmentDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
