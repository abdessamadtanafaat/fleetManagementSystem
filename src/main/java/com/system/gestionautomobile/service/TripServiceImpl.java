package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.exception.InvalidDateOrderException;
import com.system.gestionautomobile.exception.TripNotFoundException;
import com.system.gestionautomobile.exception.TripServiceException;
import com.system.gestionautomobile.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService{

    private TripRepository tripRepository;

    public void isTripValid(Trip trip){
        if(trip.getDateArrivePrevue().isEqual(trip.getDateDebut())){
            if (trip.getHeureDepart().isAfter(trip.getHeureArrivePrevue())) {
                throw new InvalidDateOrderException(trip.getDateArrivePrevue(),trip.getDateDebut(),trip.getHeureArrivePrevue(),trip.getHeureDepart());
            }
        }
        else if(trip.getDateArrivePrevue().isBefore(trip.getDateDebut())){
            throw new InvalidDateOrderException(trip.getDateArrivePrevue(),trip.getDateDebut(),trip.getHeureArrivePrevue(),trip.getHeureDepart());
        }
    }

    @Override
    public ResponseEntity<?> saveTrip(Trip trip) throws InvalidDateOrderException {
        isTripValid(trip);
        return ResponseEntity.ok(tripRepository.save(trip));
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
