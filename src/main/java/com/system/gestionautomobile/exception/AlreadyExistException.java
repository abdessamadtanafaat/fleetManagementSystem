package com.system.gestionautomobile.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException (String email){
        super(email + " already exists.");
    }

}
