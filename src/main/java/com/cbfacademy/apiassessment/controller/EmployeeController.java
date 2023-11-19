package com.cbfacademy.apiassessment.controller;

import com.cbfacademy.apiassessment.exemptionhandling.NotFoundException;
import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Api(tags = "Employee API")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "Get all employees")
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get employee by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved employee"),
            @ApiResponse(code = 404, message = "Employee not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Add a new employee")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Employee created successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee addedEmployee = employeeService.addEmployee(employee);
            return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



