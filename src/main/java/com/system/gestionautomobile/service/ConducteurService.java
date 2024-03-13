package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;

import java.util.List;

public interface ConducteurService {
    public Conducteur saveConducteur(Conducteur conducteur);
    public List<Conducteur> getAvailableConducteur(Trip trip);
}

