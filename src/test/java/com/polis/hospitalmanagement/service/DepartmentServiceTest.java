package com.polis.hospitalmanagement.service;


import com.polis.hospitalmanagement.entity.Department;
import com.polis.hospitalmanagement.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDepartmentById_Success() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Cardiology");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department found = departmentService.getDepartmentById(1L);

        assertNotNull(found);
        assertEquals("Cardiology", found.getName());
    }

    @Test
    public void testGetDepartmentById_NotFound() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            departmentService.getDepartmentById(1L);
        });

        assertEquals("Department with ID 1 not found", exception.getMessage());
    }

    @Test
    public void testCreateDepartment() {
        Department department = new Department();
        department.setName("Neurology");

        when(departmentRepository.save(department)).thenReturn(department);

        Department created = departmentService.createDepartment(department);

        assertNotNull(created);
        assertEquals("Neurology", created.getName());
    }
}

