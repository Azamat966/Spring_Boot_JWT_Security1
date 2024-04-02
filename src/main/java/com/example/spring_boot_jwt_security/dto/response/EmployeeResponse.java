package com.example.spring_boot_jwt_security.dto.response;

import lombok.Data;

@Data
public class EmployeeResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String salary;
}
