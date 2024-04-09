package com.example.spring_boot_jwt_security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private Long id;
    private Long companyid;
    private String firstName;
    private String lastName;
    private String salary;
    private String email;
}
