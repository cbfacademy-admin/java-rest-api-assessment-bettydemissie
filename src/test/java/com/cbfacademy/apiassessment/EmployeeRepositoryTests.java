package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.model.enums.Department;
import com.cbfacademy.apiassessment.repository.EmployeeRepository;
import com.cbfacademy.apiassessment.utils.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import java.util.List;
import java.util.ArrayList;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    public void testGetAllEmployees() {
        // Create a mock for the EmployeeRepository
        EmployeeRepository mockedRepository = Mockito.mock(EmployeeRepository.class);

        // Create a list of employees to be returned by the mock
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee(1, "John", "Doe", "john@example.com", Department.GROUP_FUNCTIONS));
        mockEmployees.add(new Employee(2, "Jane", "Smith", "jane@example.com", Department.PRIVATE_CORPORATE));

        // Specify the behavior you want when getAllEmployees is called
        // In this case, return the list of mock employees
        when(mockedRepository.getAllEmployees()).thenReturn(mockEmployees);

        // Call the method you want to test
        List<Employee> employees = mockedRepository.getAllEmployees();

        // Verify that the method was called
        verify(mockedRepository).getAllEmployees();

        // Assertions to check if the employee list is empty, null and size is 2
        Assertions.assertNotNull(employees);
        Assertions.assertFalse(employees.isEmpty());
        Assertions.assertEquals(2, employees.size());
    }

    @Test
    public void testGetEmployeeById() {
        EmployeeRepository mockedRepository = Mockito.mock(EmployeeRepository.class);

        // Define the behavior for an existing employee ID
        Long existingEmployeeId = 1L;
        Employee mockExistingEmployee = new Employee(existingEmployeeId, "John", "Doe", "john@example.com", Department.GROUP_FUNCTIONS);
        when(mockedRepository.getEmployeeById(existingEmployeeId)).thenReturn(mockExistingEmployee);

        // Define the behavior for a non-existing employee ID
        Long nonExistingEmployeeId = 100L;
        when(mockedRepository.getEmployeeById(nonExistingEmployeeId)).thenThrow(new NotFoundException("Employee ID not found"));

        // Test with an existing employee ID
        Employee existingEmployee = mockedRepository.getEmployeeById(existingEmployeeId);
        Assertions.assertNotNull(existingEmployee);
        Assertions.assertEquals(existingEmployeeId, existingEmployee.getId());

        // Test with a non-existing employee ID
        Assertions.assertThrows(NotFoundException.class, () -> {
            mockedRepository.getEmployeeById(nonExistingEmployeeId);
        });

        // Verify that the method was called with the specified parameters
        verify(mockedRepository, times(1)).getEmployeeById(existingEmployeeId);
        verify(mockedRepository, times(1)).getEmployeeById(nonExistingEmployeeId);
    }

    @Test
    //this is not working
    public void testAddEmployee() {
        // Create a new employee for testing
        Employee newEmployee = new Employee(22, "Martin", "Singleton", "martins@example.com", Department.ASSET_MANAGEMENT);

        // Create a mock for the EmployeeRepository
        EmployeeRepository mockedRepository = Mockito.mock(EmployeeRepository.class);

        // Specify the behavior you want when addEmployee is called
        // In this case, return the newEmployee when addEmployee is called with any Employee object
        when(mockedRepository.addEmployee(any(Employee.class))).thenReturn(newEmployee);

        // Call the method you want to test
        Employee addedEmployee = mockedRepository.addEmployee(newEmployee);

        // Verify that the method was called with the correct arguments
        verify(mockedRepository).addEmployee(newEmployee);

        // You can perform additional assertions based on your test scenario
        // For example, check if the returned employee is the expected one
        Assertions.assertEquals(newEmployee, addedEmployee);
    }

}
