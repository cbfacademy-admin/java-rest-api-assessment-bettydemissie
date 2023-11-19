package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.repository.EmployeeRepository;
import com.cbfacademy.apiassessment.utils.NotFoundException;
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
            throw new NotFoundException("Error retrieving employee by ID.");
        }
    }

    public Employee addEmployee(Employee employee) {
        try {
            return employeeRepository.addEmployee(employee);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error adding employee.", e);
        }
    }


}
