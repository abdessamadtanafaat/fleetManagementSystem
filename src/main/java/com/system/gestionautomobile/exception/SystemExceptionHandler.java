package com.system.gestionautomobile.exception;

import com.system.gestionautomobile.dto.response.ErrorResponse;
import com.system.gestionautomobile.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class SystemExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse("The Trip that you're searching for is not found !!!!" , HttpStatus.NOT_FOUND,null);
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(InvalidDateOrderException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDateOrderException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage() , HttpStatus.BAD_REQUEST,null);
        return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedTripException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedTripException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage() ,HttpStatus.NOT_FOUND,null);
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<String> handleAlreadyExistException(AlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed",HttpStatus.BAD_REQUEST, details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
