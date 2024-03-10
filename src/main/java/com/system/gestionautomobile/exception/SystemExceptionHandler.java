package com.system.gestionautomobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SystemExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTripNotFoundException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse("The Trip that you're searching for is not found !!!!" , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(VehiculeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVehiculeNotFoundException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse("The Vehicule that you're searching for is not found !!!!" , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    }

}
