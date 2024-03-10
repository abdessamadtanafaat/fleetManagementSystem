package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Trip;
import org.springframework.http.ResponseEntity;

public interface TripService {
    public ResponseEntity<?> saveTrip(Trip trip);
    public Trip getTripById(Long tripId);



}
