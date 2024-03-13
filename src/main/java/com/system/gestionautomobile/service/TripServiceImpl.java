package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.exception.DriverNotFoundException;
import com.system.gestionautomobile.exception.EntityNotFoundException;
import com.system.gestionautomobile.exception.InvalidDateOrderException;
import com.system.gestionautomobile.exception.NotFoundTripException;
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
    private ConducteurRepository conducteurRepository;

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

    public static Trip unwrappTrip(Optional<Trip> entity, long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Trip.class);

    }

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




}
