package com.example.spring_boot_jwt_security.dto.response;

import lombok.Data;

@Data
public class CompanyResponse {
    private String companyName;
    private String phone;
    private String address;
    private String directorld;
    private String directorName;
}
