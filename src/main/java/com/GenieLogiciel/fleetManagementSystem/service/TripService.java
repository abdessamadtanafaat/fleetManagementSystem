package com.GenieLogiciel.fleetManagementSystem.service;

import com.GenieLogiciel.fleetManagementSystem.Exception.*;
import com.GenieLogiciel.fleetManagementSystem.model.Trip;
import com.GenieLogiciel.fleetManagementSystem.model.VehicleType;
import com.GenieLogiciel.fleetManagementSystem.repository.DriverRepository;
import com.GenieLogiciel.fleetManagementSystem.repository.TripRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class TripService {

    @Autowired
    private final TripRepository tripRepository;
    @Autowired
    private final DriverRepository driverRepository;

    public Trip saveTrip(Trip trip) {

/*        if (trip.getDepartureDate() == null || trip.getArrivalDate() == null ||
                trip.getDepartureTime() == null || trip.getArrivalTime() == null ||
                trip.getDeparture().isEmpty() || trip.getDestination().isEmpty()) {
            throw new ValidationDataException("Trip data is incomplete");
        }*/
        if (!driverExists(trip.getDriver().getMatricule())) {
            throw new DriverNotFoundException("Driver not found");
        }
/*        if (!isSupportedVehicleType(trip.getVehicleType().toString())) {
            throw new UnsupportedVehicleTypeException("Unsupported vehicle type");
        }*/

        if (trip.getDepartureDate().isEqual(trip.getArrivalDate())) {
            if (trip.getDepartureTime().isAfter(trip.getArrivalTime())) {
                throw new InvalidDateOrderException("Arrival time cannot be before departure time");
            }
        } else if (trip.getDepartureDate().isAfter(trip.getArrivalDate())) {
            throw new InvalidDateOrderException("Start date cannot be after end date");
        }
        try {
            return tripRepository.save(trip);
        } catch (Exception e) {
            throw new TripServiceException("Failed to create trip.");
        }
    }
    private boolean driverExists(String matricule) {
        return driverRepository.existsById(matricule);
    }

    private boolean isSupportedVehicleType(String vehicleType) {
        for (VehicleType type : VehicleType.values()) {
            if (type.name().equals(vehicleType)) {
                return false;
            }
        }
        return true;
    }

/*    public Trip findTripById(long tripId) {
        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        return tripOptional.orElse(null);
    }*/
}
