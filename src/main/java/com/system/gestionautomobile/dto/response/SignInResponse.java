package com.system.gestionautomobile.dto.response;

import com.system.gestionautomobile.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private String jwt;
    private User user ;

}
