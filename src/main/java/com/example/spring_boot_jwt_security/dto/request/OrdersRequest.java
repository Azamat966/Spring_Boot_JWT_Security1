package com.example.spring_boot_jwt_security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequest {
    private String first_name;
    private String age;
    private String gmail;
    private String last_name;
}
