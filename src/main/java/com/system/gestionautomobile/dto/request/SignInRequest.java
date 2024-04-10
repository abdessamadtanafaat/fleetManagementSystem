package com.system.gestionautomobile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    //@NotBlank(message = "Email is required")
    //private String email ;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Email is required")
    private String username;
    //private String jwtToken;

}
