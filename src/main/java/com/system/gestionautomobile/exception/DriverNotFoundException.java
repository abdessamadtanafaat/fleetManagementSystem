package com.system.gestionautomobile.exception;

public class DriverNotFoundException extends RuntimeException{
    public DriverNotFoundException(){
        super("Driver Not Found");
    }
}
