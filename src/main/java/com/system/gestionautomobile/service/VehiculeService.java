package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Vehicule;

import java.util.List;

public interface VehiculeService {
    public Vehicule saveVehicule(Vehicule vehicule );
    public Vehicule getVehiculeById(long vehicleId);
    public List<Vehicule> getAvailableVehicules();
}
