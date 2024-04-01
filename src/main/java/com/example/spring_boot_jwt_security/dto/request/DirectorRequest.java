package com.example.spring_boot_jwt_security.dto.request;

import lombok.Data;

@Data
public class DirectorRequest {
    private String first_name;
    private String age;
    private String gmail;
    private String last_name;
}
