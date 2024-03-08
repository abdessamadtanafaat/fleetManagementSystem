package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Vehicule;

public interface VehiculeService {
    public Vehicule saveVehicule(Vehicule vehicule );
    public Vehicule getVehiculeById(long vehiculeId);
}
