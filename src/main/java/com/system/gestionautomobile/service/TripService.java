package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;
import org.springframework.http.ResponseEntity;

public interface TripService {
    public Trip saveTrip(Trip trip);
    public Trip getTripById(Long tripId);
    public Vehicule assignVehiculeToTrip(long tripId);
    public Conducteur assignConducteurToTrip(long tripId);

}
