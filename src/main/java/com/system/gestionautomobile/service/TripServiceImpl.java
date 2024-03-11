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

/*    @Override
    public Trip assignTripToDriver(Long tripId, Long DriverId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(NotFoundTripException::new);
        Conducteur conducteur = conducteurRepository.findById(DriverId).orElseThrow(DriverNotFoundException::new);

        if (!isDriverAvailable(conducteur, trip)) {
            throw new IllegalArgumentException("Driver is not available for this trip.");
        }
        trip.setConducteur(conducteur);
        return tripRepository.save(trip);
    }

    private boolean isDriverAvailable(Conducteur conducteur, Trip proposedTrip) {
        List<Trip> driverTrips = conducteur.getTrips().stream()
                .filter(trip -> trip.getId() != proposedTrip.getId())
                .collect(Collectors.toList());


        for (Trip trip : driverTrips) {
            if (isTripConflict(trip, proposedTrip)) {
                return false;
            }
        }
        return true;
    }
    private boolean isTripConflict(Trip existingTrip, Trip proposedTrip) {

        boolean intervalConflict = isIntervalWithinInterval(existingTrip.getDateDebut(), existingTrip.getHeureDepart(),
                existingTrip.getDateArrivePrevue(), existingTrip.getHeureArrivePrevue(),
                proposedTrip.getDateDebut(), proposedTrip.getHeureDepart(),
                proposedTrip.getDateArrivePrevue(), proposedTrip.getHeureArrivePrevue());

        return intervalConflict;
    }

    private boolean isIntervalWithinInterval(LocalDate start1, LocalTime startTime1, LocalDate end1, LocalTime endTime1,
                                             LocalDate start2, LocalTime startTime2, LocalDate end2, LocalTime endTime2) {
        LocalDateTime intervalStart1 = LocalDateTime.of(start1, startTime1);
        LocalDateTime intervalEnd1 = LocalDateTime.of(end1, endTime1);
        LocalDateTime intervalStart2 = LocalDateTime.of(start2, startTime2);
        LocalDateTime intervalEnd2 = LocalDateTime.of(end2, endTime2);
        return intervalStart1.isBefore(intervalEnd2) && intervalEnd1.isAfter(intervalStart2);
    }*/

}
