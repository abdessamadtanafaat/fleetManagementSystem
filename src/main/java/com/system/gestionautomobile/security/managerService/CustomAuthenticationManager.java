package com.system.gestionautomobile.security.managerService;


import com.system.gestionautomobile.dto.request.SignInRequest;
import com.system.gestionautomobile.dto.request.SignUpRequest;
import com.system.gestionautomobile.dto.response.SignInResponse;
import com.system.gestionautomobile.entity.User;
import com.system.gestionautomobile.entity.UserRole;
import com.system.gestionautomobile.exception.AlreadyExistException;
import com.system.gestionautomobile.exception.NotFoundException;
import com.system.gestionautomobile.repository.UserRepository;
import com.system.gestionautomobile.security.BlacklistToken.BlacklistRepository;
import com.system.gestionautomobile.security.SecurityUtils;
import com.system.gestionautomobile.security.filter.AuthenticationFilter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager, ICustomAuthenticationManager {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private BlacklistRepository blacklistRepository;
    private SecurityUtils securityUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
/*        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundEmailException(authentication.getName()));*/

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new NotFoundException(authentication.getName()));

        if(!passwordEncoder.matches(authentication.getCredentials().toString(),user.getPassword())){
            throw new BadCredentialsException("You provided an incorrect password");
        }

        // Create authorities for the authenticated user
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.name()));
        authorities.add(new SimpleGrantedAuthority(UserRole.ROLE_CONDUCTOR.name()));

        user.setActive(true);

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword(),authorities);
    }
    @Override
    public String signUp(SignUpRequest signUpRequest) {

        boolean userExists = userRepository.findByEmail(signUpRequest.getEmail()).isPresent();
        if (userExists) {
            throw new AlreadyExistException(signUpRequest.getEmail() + " Already Exists");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .lastname(signUpRequest.getLastname())
                .firstname(signUpRequest.getFirstname())
                .username(signUpRequest.getUsername()).
                build();

        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "User Created Successfully";
    }

    @Override
    public String logout(String username, String jwtToken) {
        try{
            Optional<User> userOptional = userRepository.findByUsername(username);
            User user = userOptional.orElseThrow(() -> new NotFoundException(username));

            if(!blacklistRepository.existsByToken(jwtToken)) {
                securityUtils.addToBlacklist(jwtToken);
                user.setActive(false);
                user.setLastLogout(LocalDateTime.now());
                userRepository.save(user);
                return "Logged out successfully";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
