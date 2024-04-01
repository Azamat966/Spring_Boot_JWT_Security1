package com.example.spring_boot_jwt_security.dto.request;

import lombok.Data;

@Data
public class ClientRequest {
    private String companyId;
    private String name;
    private String email;
}
