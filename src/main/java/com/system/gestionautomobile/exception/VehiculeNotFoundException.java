package com.system.gestionautomobile.exception;

public class VehiculeNotFoundException extends RuntimeException{
    public VehiculeNotFoundException(long id ){
        super("The vehicule with id = "+id+" is not found!!!!!");

    }
}
