package com.system.gestionautomobile.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.gestionautomobile.dto.request.SignInRequest;
import com.system.gestionautomobile.dto.response.SignInResponse;
import com.system.gestionautomobile.entity.User;
import com.system.gestionautomobile.repository.UserRepository;
import com.system.gestionautomobile.security.SecurityConstants;
import com.system.gestionautomobile.security.managerService.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomAuthenticationManager authenticationManager ;
    private final UserRepository userRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try{
            SignInRequest user = new ObjectMapper().readValue(request.getInputStream() , SignInRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername() ,user.getPassword());
            return authenticationManager.authenticate(authentication);
        }
        catch(IOException ex){
            throw new RuntimeException();

        }

    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
    try {
        String username = authResult.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            String token = generateToken(user);
            sendAuthenticationResponse(response, token, user);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
    public String generateToken(User user) {

    return JWT.create()
            .withSubject(user.getUsername())
            .withClaim("firstname", user.getFirstname())
            .withClaim("lastname", user.getLastname())
            .withClaim("email", user.getEmail())
            .withClaim("roles", user.getRole().toString())
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
            .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY.getEncoded()));
}

    private void sendAuthenticationResponse(HttpServletResponse response,
                                            String token,
                                            User user) throws IOException {

        SignInResponse authenticationResponse = new SignInResponse(token, user);
        String jsonResponse = new ObjectMapper().writeValueAsString(authenticationResponse);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }


}
