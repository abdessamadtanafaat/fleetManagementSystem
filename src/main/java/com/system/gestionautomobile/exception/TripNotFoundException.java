package com.system.gestionautomobile.exception;

public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(long id){
        super("The Trip with "+id + " Is not found in the database");

    }
}
