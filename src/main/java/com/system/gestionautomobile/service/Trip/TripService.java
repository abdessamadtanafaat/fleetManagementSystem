package com.system.gestionautomobile.service.Trip;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TripService {
    public Trip saveTrip(Trip trip);
    public Trip getTripById(Long tripId);
    public Vehicule assignVehiculeToTrip(long tripId , long vehiculeId);
    public Conducteur assignConducteurToTrip(long tripId , long conducteurId);
    public List<Vehicule> getAvailableVehicules(long tripId);
    public List<Conducteur> getAvailableConducteurs(long tripId);

}
