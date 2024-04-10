package com.system.gestionautomobile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


public enum UserRole implements GrantedAuthority{
    ROLE_ADMIN,
    ROLE_CONDUCTOR;
    @Override
    public String getAuthority() {
        return name();
    }
}
