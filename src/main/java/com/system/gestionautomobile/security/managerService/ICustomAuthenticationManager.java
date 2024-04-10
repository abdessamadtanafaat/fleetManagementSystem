package com.system.gestionautomobile.security.managerService;

import com.system.gestionautomobile.dto.request.SignUpRequest;
import com.system.gestionautomobile.entity.User;

public interface ICustomAuthenticationManager {
    public String signUp(SignUpRequest signUpRequest) ;
    public String logout(String email, String jwtToken) ;

    }
