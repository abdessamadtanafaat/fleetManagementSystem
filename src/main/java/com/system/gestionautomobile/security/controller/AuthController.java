package com.system.gestionautomobile.security.controller;

import com.system.gestionautomobile.dto.request.SignInRequest;
import com.system.gestionautomobile.dto.request.SignUpRequest;
import com.system.gestionautomobile.dto.response.SignInResponse;
import com.system.gestionautomobile.security.managerService.CustomAuthenticationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @PostMapping("/signUp")
    public ResponseEntity<String> SignUp(
            @Valid
            @RequestBody SignUpRequest signUpRequest
    ) {
        String responseMessage = customAuthenticationManager.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
    @PostMapping("/logOut")
    public ResponseEntity<String> logout(@RequestBody SignInRequest signInRequest , HttpServletRequest request){
        return new ResponseEntity<>(customAuthenticationManager.logout(signInRequest.getUsername(), request.getHeader("Authorization").substring(7)), HttpStatus.OK);
    }

}
