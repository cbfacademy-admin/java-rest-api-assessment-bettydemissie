package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.exemptionhandling.AlreadyExistsExemption;
import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.repository.EmployeeRepository;
import com.cbfacademy.apiassessment.exemptionhandling.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        try {
            return employeeRepository.getAllEmployees();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error retrieving all employees.", e);
        }
    }

    public Employee getEmployeeById(Long employeeId) {
        try {
            return employeeRepository.getEmployeeById(employeeId);
        } catch (NotFoundException e) {
            throw e; //Re-throe NotFoundExemption
        } catch (RuntimeException e) {
            throw new RuntimeException("Error retrieving employee by ID.", e);
        }
    }

    public Employee addEmployee(Employee employee) {
        try {
            return employeeRepository.addEmployee(employee);
        } catch (AlreadyExistsExemption e) {
            throw e; // Re-throw AlreadyExistsExemption directly
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while adding employee.", e);
        }
    }
}
