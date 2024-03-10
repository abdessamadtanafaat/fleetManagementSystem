package com.system.gestionautomobile.exception;

import java.time.LocalDate;
import java.time.LocalTime;

public class InvalidDateOrderException extends RuntimeException{
<<<<<<< HEAD
    public InvalidDateOrderException(String message){super(message);}

=======
    public InvalidDateOrderException(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime arrivalTime) {
        super(String.format("Start date %s at time %s cannot be after end date %s at time %s", startDate, startTime, endDate, arrivalTime));
    }
>>>>>>> d3c15cc3662f9ca38da23124591f2d61aaf6a03a
}
