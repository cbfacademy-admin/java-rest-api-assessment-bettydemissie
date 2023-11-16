package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.repository.EmployeeRepository;
import com.cbfacademy.apiassessment.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeByID(int ID) {
        return employeeRepository.getEmployeeByID(ID);
    }


    public Employee addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

}
