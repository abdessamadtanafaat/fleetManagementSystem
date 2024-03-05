package com.GenieLogiciel.fleetManagementSystem.dto.request;

import com.GenieLogiciel.fleetManagementSystem.model.VehicleType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TripRequestDTO {
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String departure;
    private String destination;
    private VehicleType vehicleType;
    private int numberOfPassenger;
}
