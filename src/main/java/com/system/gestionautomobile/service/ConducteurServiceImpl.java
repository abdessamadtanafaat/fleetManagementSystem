package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.exception.EntityNotFoundException;
import com.system.gestionautomobile.repository.ConducteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ConducteurServiceImpl implements ConducteurService {
    @Autowired
    private ConducteurRepository conducteurRepository;

    @Override
    public Conducteur saveConducteur(Conducteur conducteur){
        return conducteurRepository.save(conducteur);
    }

    @Override
    public List<Conducteur> getAvailableConducteur(Trip trip) {
        List<Conducteur> conducteurs = getAllConducteur();
        //implements stream
        conducteurs.stream().filter(conducteur->isConducteurAvailable(conducteur ,trip.getDateDebut() , trip.getDateArrivePrevue()));
        return conducteurs;
    }
    public boolean isConducteurAvailable(Conducteur conducteur , LocalDate dateDebut , LocalDate dateArrive){
        Set<Trip> trips = conducteur.getTrips();
        trips.stream().filter(trip->compareToNewTrip(trip , dateDebut , dateArrive));
        return trips.isEmpty();
    }
    public boolean compareToNewTrip(Trip trip , LocalDate dateDebut , LocalDate datePrevue){
        if(dateDebut.isAfter(trip.getDateDebut().minusDays(1)) && dateDebut.isBefore(trip.getDateArrivePrevue().plusDays(1))  )return false;
        if(datePrevue.isAfter(trip.getDateDebut().minusDays(1)) && datePrevue.isBefore(trip.getDateArrivePrevue().plusDays(1)))return false;
        return true ;
    }


    public List<Conducteur> getAllConducteur(){
        return (List<Conducteur>)conducteurRepository.findAll();

    }

    public void deleteConducteur(Long id) {
        conducteurRepository.deleteById(id);
    }

    public Conducteur getConducteurById(long id){

        Optional<Conducteur> entity = conducteurRepository.findById(id);
        return unwrappedConducteur(entity , id);

    }

    public static Conducteur unwrappedConducteur(Optional<Conducteur> entity , long id){
        if(entity.isPresent())return entity.get();
        else throw new EntityNotFoundException(id ,Conducteur.class);
    }



}
