package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Trip;

public interface TripService {
    public Trip saveTrip(Trip trip);
    public Trip getTripById(Long tripId);


}
