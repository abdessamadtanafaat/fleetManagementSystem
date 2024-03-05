package com.GenieLogiciel.fleetManagementSystem.service;

import com.GenieLogiciel.fleetManagementSystem.Exception.TripServiceException;
import com.GenieLogiciel.fleetManagementSystem.model.Trip;
import com.GenieLogiciel.fleetManagementSystem.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {
    private final TripRepository tripRepository;
    @Autowired
    public TripService(TripRepository tripRepository)
    {
        this.tripRepository = tripRepository;
    }
    public Trip saveTrip(Trip trip) {
        try {
            return tripRepository.save(trip);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirements
            throw new TripServiceException("Failed to save trip", e);
        }
    }
}
