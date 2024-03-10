package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.exception.EntityNotFoundException;
import com.system.gestionautomobile.exception.InvalidDateOrderException;
import com.system.gestionautomobile.repository.ConducteurRepository;
import com.system.gestionautomobile.repository.TripRepository;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> d3c15cc3662f9ca38da23124591f2d61aaf6a03a
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService{

    private TripRepository tripRepository;
    private ConducteurRepository conducteurRepository ;

    public void isTripValid(Trip trip){
        if(trip.getDateArrivePrevue().isEqual(trip.getDateDebut())){
            if (trip.getHeureDepart().isAfter(trip.getHeureArrivePrevue())) {
                throw new InvalidDateOrderException(trip.getDateArrivePrevue(),trip.getDateDebut(),trip.getHeureArrivePrevue(),trip.getHeureDepart());
            }
        }
        else if(trip.getDateArrivePrevue().isBefore(trip.getDateDebut())){
<<<<<<< HEAD
            throw new InvalidDateOrderException("Start date cannot be after end date");
=======
            throw new InvalidDateOrderException(trip.getDateArrivePrevue(),trip.getDateDebut(),trip.getHeureArrivePrevue(),trip.getHeureDepart());
>>>>>>> d3c15cc3662f9ca38da23124591f2d61aaf6a03a
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
<<<<<<< HEAD
        else throw new EntityNotFoundException(id , Trip.class);

    }
=======
        else throw new TripNotFoundException(id);
    }

>>>>>>> d3c15cc3662f9ca38da23124591f2d61aaf6a03a
}
