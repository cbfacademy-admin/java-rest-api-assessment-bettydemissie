package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.utils.EmployeeConverter;
import com.cbfacademy.apiassessment.utils.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private String filePath = "src/main/resources/employee.json";

    @Autowired
    private EmployeeConverter employeeConverter;

    private static final Logger log = LoggerFactory.getLogger(EmployeeRepository.class);
    public EmployeeRepository(@Value("${file.path.employee}") String filePath) {
        this.filePath = filePath;
    }

    public List<Employee> getAllEmployees() {
        try {
            return employeeConverter.readJsonFile(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error reading employees from file.", e);
        }
    }
    //Retrieves the details of an employee when provided their id
    public Employee getEmployeeById(Long employeeId) {
        try {
            List<Employee> employees = getAllEmployees();
            if (!checkEmployeeExist(employeeId)) {
                throw new NotFoundException("Employee ID not found");
            }
            // Use stream and filter to find the employee by ID
            for (Employee employee : employees) {
                if (employee.getId().equals(employeeId)) {
                    return employee;
                }
            }
        } catch (Exception e) {
            log.error("An error occurred while processing the request");
            throw new NotFoundException("An error occurred while processing the request");
        }
        return null;
    }

    public Employee addEmployee(Employee employee) {
        try {
            List<Employee> employees = getAllEmployees();
            if (employees == null) {
                throw new IllegalStateException("Error reading existing employees from the file.");
            }
            if (checkEmployeeExist(employee.getId())) {
                throw new IllegalStateException("Employee with ID " + employee.getId() + " already exists.");
            }
            employees.add(employee);
            employeeConverter.writeJsonFile(employees, filePath);
            return employee;
        } catch (Exception e) {
            log.error("Unexpected error while adding employee.", e);
            throw new RuntimeException("Unexpected error while adding employee.", e);
        }
    }

    //Internal method to check if the employee exists based off id
    public boolean checkEmployeeExist(Long employeeId) {
        List<Employee> employees = getAllEmployees();
        // Check if the provided employeeId exists in the employee json
        return employees.stream()
                .anyMatch(employee -> employee.getId().equals(employeeId));
    }
}

