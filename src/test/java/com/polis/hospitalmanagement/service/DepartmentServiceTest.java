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

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDepartmentById_Success() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("Cardiology");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        // Act
        Department result = departmentService.getDepartmentById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Cardiology", result.getName());
    }

    @Test
    void testGetDepartmentById_NotFound() {
        // Arrange
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            departmentService.getDepartmentById(1L);
        });

        assertEquals("Department not found", exception.getMessage());
    }


}
