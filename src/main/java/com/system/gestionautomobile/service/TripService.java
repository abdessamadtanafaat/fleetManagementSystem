package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import org.springframework.http.ResponseEntity;

public interface TripService {
    public Trip saveTrip(Trip trip);
    public Trip getTripById(Long tripId);
    public Trip assignTripToDriver(Long tripId, Long driverId);

}
