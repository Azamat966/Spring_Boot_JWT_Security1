package com.example.spring_boot_jwt_security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootJwtSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtSecurityApplication.class, args);
    }

}
