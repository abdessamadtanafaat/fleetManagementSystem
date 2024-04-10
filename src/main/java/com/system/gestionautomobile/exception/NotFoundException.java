package com.system.gestionautomobile.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class NotFoundException extends BadCredentialsException {
    public NotFoundException(String username) {
        super("The " + username + " not found.");
    }


}
