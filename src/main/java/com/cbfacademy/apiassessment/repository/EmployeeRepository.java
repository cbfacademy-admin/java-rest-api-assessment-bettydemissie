package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.utils.EmployeeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private String filePath = "src/main/resources/employee.json";

    @Autowired
    private EmployeeConverter employeeConverter;

    public EmployeeRepository(@Value("${file.path.employee}") String filePath) {
        this.filePath = filePath;
    }

    public List<Employee> getAllEmployees() {
        return employeeConverter.readJsonFile(filePath);
    }

    public Employee getEmployeeById(Long ID) {

        List<Employee> employees = getAllEmployees();
        // Use stream and filter to find the employee by ID
        return employees.stream()
                .filter(employee -> employee.getId().equals(ID))
                .findFirst()
                .orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        // Read existing employees from the file
        List<Employee> employees = getAllEmployees();

        // Check if the list is null (indicating an error reading the file)
        if (employees == null) {
            // Handle the error, for example, throw an exception or log a message
            throw new RuntimeException("Error reading existing employees from the file.");
        }
        // Add the new employee to the list
        employees.add(employee);

        // Write the updated list of employees back to the file
        employeeConverter.writeJsonFile(employees, filePath);

        // Return the added employee
        return employee;
    }

    public boolean checkEmployeeExist(Long employeeId) {
        List<Employee> employees = getAllEmployees();
        // Use stream API to check if the provided employeeId exists in any assignedTo field
        return employees.stream()
                .anyMatch(employee -> employee.getId().equals(employeeId));
    }
}

