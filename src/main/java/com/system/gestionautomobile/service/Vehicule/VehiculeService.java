package com.system.gestionautomobile.service.Vehicule;

import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;

import java.util.List;

public interface VehiculeService {
    public Vehicule saveVehicule(Vehicule vehicule );
    public void deleteVehicule(long vehiculeId);
    public Vehicule getVehiculeById(long vehicleId);
    public List<Vehicule> getAvailableVehicules(Trip trip);

}
