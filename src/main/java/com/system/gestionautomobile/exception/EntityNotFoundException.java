package com.system.gestionautomobile.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
    }
    public EntityNotFoundException(String username) {
        super("The " + username + "' does not exist in our records");
    }
    public EntityNotFoundException() {
        super("Invalid Credentials");
    }

}