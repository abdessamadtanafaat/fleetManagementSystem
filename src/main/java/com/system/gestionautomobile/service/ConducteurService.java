package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;

import java.util.List;

public interface ConducteurService {
    public Conducteur getConducteurById(long id);
    public Conducteur saveConducteur(Conducteur conducteur);
    public void deleteConducteur(long conducteurId);
    public List<Conducteur> getAllConducteurs();

    public List<Conducteur> getAvailableConducteurs(Trip trip);
}

