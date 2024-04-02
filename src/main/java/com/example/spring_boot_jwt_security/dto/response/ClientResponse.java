package com.example.spring_boot_jwt_security.dto.response;

import lombok.Data;

@Data
public class ClientResponse {
    private Long id;
    private String companyId;
    private String email;
    private String name;
}
