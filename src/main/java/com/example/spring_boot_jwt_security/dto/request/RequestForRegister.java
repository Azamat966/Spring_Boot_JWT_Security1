package com.example.spring_boot_jwt_security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestForRegister {
    private String name;
    private String email;
    private String password;

    public ResponseEntity<String> create(RequestForRegister request) {
        return null;
    }
}
