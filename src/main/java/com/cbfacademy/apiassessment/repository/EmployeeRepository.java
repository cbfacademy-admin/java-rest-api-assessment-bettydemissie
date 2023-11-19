package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.utils.EmployeeConverter;
import com.cbfacademy.apiassessment.utils.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
            // Handle the exception according to your application's requirements
            e.printStackTrace(); // Consider logging the exception
            throw new RuntimeException("An error occurred while processing the request");
        }
        return null;
    }


    public Employee addEmployee(Employee employee) {
        List<Employee> employees = getAllEmployees();

        if (employees == null) {
            //throw an exception if no employees are in json
            throw new RuntimeException("Error reading existing employees from the file.");
        }

        employees.add(employee);
        // Write the updated list of employees back to the file
        employeeConverter.writeJsonFile(employees, filePath);

        return employee;
    }

    //Internal method to check if the employee exists based off id
    public boolean checkEmployeeExist(Long employeeId) {
        List<Employee> employees = getAllEmployees();
        // Check if the provided employeeId exists in the employee json
        return employees.stream()
                .anyMatch(employee -> employee.getId().equals(employeeId));
    }

}

