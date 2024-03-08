package com.system.gestionautomobile.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private HttpStatus status;
}
