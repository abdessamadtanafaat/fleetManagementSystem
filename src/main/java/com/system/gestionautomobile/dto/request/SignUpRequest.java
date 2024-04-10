package com.system.gestionautomobile.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    //@Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "FirstName is required")
    private String firstname;

    @NotBlank(message = "LastName is required")
    private String lastname;
}
