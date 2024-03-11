package com.system.gestionautomobile.exception;

import java.time.LocalDate;
import java.time.LocalTime;

public class InvalidDateOrderException extends RuntimeException{
    //public InvalidDateOrderException(String message){super(message);}
    public InvalidDateOrderException(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime arrivalTime) {
        super(String.format("Start date %s at time %s cannot be after end date %s at time %s", startDate, startTime, endDate, arrivalTime));
    }
}
