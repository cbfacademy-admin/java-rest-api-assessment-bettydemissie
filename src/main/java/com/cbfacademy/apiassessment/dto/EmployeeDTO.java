package com.cbfacademy.apiassessment.dto;

public class EmployeeDTO {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String department;

    // Constructors, getters, and setters
    // You can generate these using your IDE or write them manually

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
