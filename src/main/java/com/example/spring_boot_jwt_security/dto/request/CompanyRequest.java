package com.example.spring_boot_jwt_security.dto.request;

import lombok.Data;

@Data
public class CompanyRequest {
    private String companyName;
    private String phone;
    private String address;
    private String directorld;
}
