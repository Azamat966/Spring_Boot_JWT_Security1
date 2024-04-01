package com.example.spring_boot_jwt_security.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,ADMIN,GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
