package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.model.enums.Department;
import com.cbfacademy.apiassessment.repository.EmployeeRepository;
import com.cbfacademy.apiassessment.utils.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        // Add assertions to check if the list is not null or empty, etc.
        Assertions.assertNotNull(employees);
        // Add more assertions based on your requirements

    }

    @Test
    public void testGetEmployeeById() {
        Long existingEmployeeId = 1L;
        Long nonExistingEmployeeId = 100L;

        // Test with an existing employee ID
        Employee existingEmployee = employeeRepository.getEmployeeById(existingEmployeeId);
        Assertions.assertNotNull(existingEmployee);
        Assertions.assertEquals(existingEmployeeId, existingEmployee.getId());

        // Test with a non-existing employee ID
        Assertions.assertThrows(NotFoundException.class, () -> {
            employeeRepository.getEmployeeById(nonExistingEmployeeId);
        });
    }

    @Test
    public void testAddEmployee() {
        // Create a new employee for testing
        Employee newEmployee = new Employee( 21L, "Sam", "Smith", "ssmith@example.com", Department.PRIVATE_CORPORATE);
        Employee addedEmployee = employeeRepository.addEmployee(newEmployee);

        // Retrieve the list of all employees and check if the new employee is added
        List<Employee> allEmployees = employeeRepository.getAllEmployees();
        Assertions.assertTrue(allEmployees.contains(addedEmployee));
    }

    @Test
    public void testCheckEmployeeExist() {
        Long existingEmployeeId = 1L;
        Long nonExistingEmployeeId = 100L;

        // Test with an existing employee ID
        boolean existingEmployeeExists = employeeRepository.checkEmployeeExist(existingEmployeeId);
        Assertions.assertTrue(existingEmployeeExists);

        // Test with a non-existing employee ID
        boolean nonExistingEmployeeExists = employeeRepository.checkEmployeeExist(nonExistingEmployeeId);
        Assertions.assertFalse(nonExistingEmployeeExists);
    }
}
