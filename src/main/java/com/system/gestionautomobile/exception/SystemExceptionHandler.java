package com.system.gestionautomobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SystemExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse("The Trip that you're searching for is not found !!!!" , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);

    }
<<<<<<< HEAD
    @ExceptionHandler(InvalidDateOrderException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDateOrderException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage() , HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);

=======
    @ExceptionHandler(VehiculeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVehiculeNotFoundException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse("The Vehicule that you're searching for is not found !!!!" , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
>>>>>>> d3c15cc3662f9ca38da23124591f2d61aaf6a03a
    }

}
