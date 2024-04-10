package com.system.gestionautomobile.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "user")

public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")

    private String email;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "username")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(name = "last_login")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime lastLogin;

    @Column(name = "is_active")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private boolean isActive;

    @Column(name = "last_logout")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime lastLogout;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;


}