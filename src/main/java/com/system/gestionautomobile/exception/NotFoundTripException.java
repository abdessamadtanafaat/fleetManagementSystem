package com.system.gestionautomobile.exception;

public class NotFoundTripException extends RuntimeException {
    public NotFoundTripException() {
        super("Trip not found");
    }
}
