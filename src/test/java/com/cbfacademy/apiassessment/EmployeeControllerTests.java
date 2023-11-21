package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.controller.EmployeeController;
import com.cbfacademy.apiassessment.model.entities.Employee;
import com.cbfacademy.apiassessment.model.enums.Department;
import com.cbfacademy.apiassessment.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class EmployeeControllerTests {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        // Initialize mocks and setup mockMvc before each test
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        // Mock data
        Employee one = new Employee(1, "Martin", "Singleton", "martins@example.com", Department.ASSET_MANAGEMENT);
        Employee two = new Employee(2, "Jane", "Doe", "jane@example.com", Department.PRIVATE_CORPORATE);

        List<Employee> employees = new ArrayList<>();
        employees.add(one);
        employees.add(two);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Perform the HTTP GET request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstname").value("Martin"))
                .andExpect(jsonPath("$[0].lastname").value("Singleton"))
                .andExpect(jsonPath("$[0].email").value("martins@example.com"))
                .andExpect(jsonPath("$[0].department").value("ASSET_MANAGEMENT"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstname").value("Jane"))
                .andExpect(jsonPath("$[1].lastname").value("Doe"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"))
                .andExpect(jsonPath("$[1].department").value("PRIVATE_CORPORATE"))
                .andDo(print()); // Log the request and response
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        // Mock data
        long employeeId = 1L;
        Employee employee = new Employee(employeeId, "John", "Doe", "john.doe@example.com", Department.GROUP_FUNCTIONS);

        when(employeeService.getEmployeeById(employeeId)).thenReturn(employee);

        // Perform the HTTP GET request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/{employeeId}", employeeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.department").value("GROUP_FUNCTIONS"))
                .andDo(print());
    }

    @Test
    public void testAddEmployee() throws Exception {
        // Mock data
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com", Department.GROUP_FUNCTIONS);

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);

        // Perform the HTTP POST request and validate the response
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"firstname\":\"John\",\"lastname\":\"Doe\",\"email\":\"john.doe@example.com\",\"department\":\"GROUP_FUNCTIONS\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.department").value("GROUP_FUNCTIONS"))
                .andDo(print()) // Log the request and response
                .andReturn();

        verify(employeeService, times(1)).addEmployee(any(Employee.class));
    }
}

