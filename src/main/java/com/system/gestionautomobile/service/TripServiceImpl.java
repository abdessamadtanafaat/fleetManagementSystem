package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.exception.*;
import com.system.gestionautomobile.repository.ConducteurRepository;
import com.system.gestionautomobile.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;
    private ConducteurService conducteurService ;
    private VehiculeService vehiculeService;

    public void isTripValid(Trip trip) {
        if (trip.getDateArrivePrevue().isEqual(trip.getDateDebut())) {
            if (trip.getHeureDepart().isAfter(trip.getHeureArrivePrevue())) {
                throw new InvalidDateOrderException(trip.getDateArrivePrevue(), trip.getDateDebut(), trip.getHeureArrivePrevue(), trip.getHeureDepart());
            }
        } else if (trip.getDateArrivePrevue().isBefore(trip.getDateDebut())) {

            throw new InvalidDateOrderException(trip.getDateArrivePrevue(), trip.getDateDebut(), trip.getHeureArrivePrevue(), trip.getHeureDepart());
        }
    }

    @Override
    public Trip saveTrip(Trip trip) throws InvalidDateOrderException {
        isTripValid(trip);
        return tripRepository.save(trip);
    }

    @Override
    public Trip getTripById(Long tripId) {
        Optional<Trip> entity = tripRepository.findById(tripId);
        return unwrappTrip(entity, tripId);
    }

    @Override
    public Vehicule assignVehiculeToTrip(long tripId , long vehiculeId) {
        Trip trip =getTripById(tripId);
        Vehicule vehicule  = vehiculeService.getVehiculeById(vehiculeId);
        trip.setVehicule(vehicule);
        vehicule.getTrips().add(trip);
        vehicule.setDisponible(false);
        return vehiculeService.saveVehicule(vehicule);

//        throw new UnsupportedTripException("No available Vehicules for the trip with id of "+tripId);
    }


    @Override
    public Conducteur assignConducteurToTrip(long tripId ,long conducteurId ) {
        Trip trip = getTripById(tripId);
        Conducteur conducteur = conducteurService.getConducteurById(conducteurId);
        trip.setConducteur(conducteur);
        conducteur.getTrips().add(trip);
        return conducteurService.saveSimple(conducteur);



//        throw new UnsupportedTripException("No available Conducteur for the trip with id of "+tripId);
    }

    @Override
    public List<Vehicule> getAvailableVehicules(long tripId) {
        Trip trip =getTripById(tripId);
        return vehiculeService.getAvailableVehicules(trip);
    }

    @Override
    public List<Conducteur> getAvailableConducteurs(long tripId) {
        Trip trip = getTripById(tripId);
        return conducteurService.getAvailableConducteurs(trip);
    }


    public static Trip unwrappTrip(Optional<Trip> entity, long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Trip.class);

    }

/*
    @Override
    public Trip assignTripToDriver(Long tripId, Long driverId) {
        // Retrieve the trip and conducteur from the database
        Trip proposedTrip = tripRepository.findById(tripId).orElseThrow(NotFoundTripException::new);
        Conducteur conducteur = conducteurRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);

        // Check if the conducteur is available
        if (isDriverAvailable(conducteur, proposedTrip)) {
            proposedTrip.setConducteur(conducteur);
            return tripRepository.save(proposedTrip);
        } else {
            throw new RuntimeException("Conducteur is busy and cannot be assigned this trip.");
        }
    }

    private boolean isDriverAvailable(Conducteur conducteur, Trip proposedTrip) {
        LocalDateTime proposedTripStart = LocalDateTime.of(proposedTrip.getDateDebut(), proposedTrip.getHeureDepart());
        LocalDateTime proposedTripEnd = LocalDateTime.of(proposedTrip.getDateArrivePrevue(), proposedTrip.getHeureArrivePrevue());

        // Filter the conducteur's trips that overlap with the proposed trip
        boolean isAvailable = conducteur.getTrips().stream()
                .noneMatch(existingTrip -> {
                    LocalDateTime existingTripStart = LocalDateTime.of(existingTrip.getDateDebut(), existingTrip.getHeureDepart());
                    LocalDateTime existingTripEnd = LocalDateTime.of(existingTrip.getDateArrivePrevue(), existingTrip.getHeureArrivePrevue());
                    return proposedTripStart.isBefore(existingTripEnd) && proposedTripEnd.isAfter(existingTripStart);
                });

        // If the conducteur has no overlapping trips, then they are available
        return isAvailable;
    }
*/




}
