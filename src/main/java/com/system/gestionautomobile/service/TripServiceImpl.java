package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.exception.InvalidDateOrderException;
import com.system.gestionautomobile.exception.TripNotFoundException;
import com.system.gestionautomobile.exception.TripServiceException;
import com.system.gestionautomobile.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripServiceImpl implements TripService{
    @Autowired
    private TripRepository tripRepository;

    public void isTripValid(Trip trip){

        if(trip.getDateArrivePrevue().isEqual(trip.getDateDebut())){
            if (trip.getHeureDepart().isAfter(trip.getHeureArrivePrevue())) {
                throw new InvalidDateOrderException("Arrival time cannot be before departure time");
            }

        }
        else if(trip.getDateArrivePrevue().isBefore(trip.getDateDebut())){
            throw new InvalidDateOrderException("Start date cannot be after end date");

        }

    }
    public Trip saveTrip(Trip trip){
        //fonction
        isTripValid(trip);
        return tripRepository.save(trip);
    }

    @Override
    public Trip getTripById(Long tripId) {
        Optional<Trip> entity = tripRepository.findById(tripId);
        return unwrappTrip(entity , tripId);
    }

    public static Trip unwrappTrip(Optional<Trip> entity , long id ){
        if(entity.isPresent())return entity.get();
        else throw new TripNotFoundException(id);

    }


}
