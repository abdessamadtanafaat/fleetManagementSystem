package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TripService {
    public Trip saveTrip(Trip trip);
    public void deleteTrip(long tripId);
    public Trip getTripById(Long tripId);
    public List<Trip> getAllTrips();
    public Trip assignConducteurToTrip(Long tripId);
    public Vehicule assignVehiculeToTrip(long tripId);
}
