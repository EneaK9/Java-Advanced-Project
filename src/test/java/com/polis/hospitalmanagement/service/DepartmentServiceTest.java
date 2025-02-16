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

/**
 * Unit test class for DepartmentService.
 * Uses JUnit and Mockito for mocking dependencies.
 */
class DepartmentServiceTest {

    @Mock // Mocks the DepartmentRepository (prevents actual database interaction)
    private DepartmentRepository departmentRepository;

    @InjectMocks // Automatically injects the mock repository into DepartmentService
    private DepartmentService departmentService;

    /**
     * Initializes Mockito before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
    }

    /**
     * Test case: When a department exists, it should be found by ID.
     */
    @Test
    void testGetDepartmentById_Success() {
        // Arrange: Create a new department object
        Department department = new Department();
        department.setId(1L); // Assign an ID
        department.setName("Cardiology"); // Assign a department name

        // Mocking repository behavior: When findById(1L) is called, return the department object
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        // Act: Call the service method
        Department result = departmentService.getDepartmentById(1L);

        // Assert: Verify expected behavior
        assertNotNull(result); // Ensure that a department is returned
        assertEquals("Cardiology", result.getName()); // Verify that the department name matches
    }

    /**
     * Test case: When a department does not exist, an exception should be thrown.
     */
    @Test
    void testGetDepartmentById_NotFound() {
        // Arrange: Mocking repository behavior to return empty when department is not found
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert: Verify that calling getDepartmentById(1L) throws a RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            departmentService.getDepartmentById(1L);
        });

        // Check that the exception message is correct
        assertEquals("Department not found", exception.getMessage());
    }
}
