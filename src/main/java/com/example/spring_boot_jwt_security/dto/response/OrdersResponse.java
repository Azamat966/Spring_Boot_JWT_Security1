package com.example.spring_boot_jwt_security.dto.response;

import lombok.Data;

@Data
public class OrdersResponse {
    private String order ;
    private String message;
    public OrdersResponse(String message) {
        this.message = message;
    }


}
