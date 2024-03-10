package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.exception.TripNotFoundException;
import com.system.gestionautomobile.exception.VehiculeNotFoundException;
import com.system.gestionautomobile.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class VehiculeServiceImpl implements VehiculeService {
    @Autowired
    private VehiculeRepository vehiculeRepository;
    public Vehicule saveVehicule(Vehicule vehicule ){
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule getVehiculeById(long vehicleId) {
        Optional<Vehicule> entity = vehiculeRepository.findById(vehicleId);
        return unwrappVehicule(entity, vehicleId);

    }

    public static  Vehicule unwrappVehicule(Optional<Vehicule> entity , long id){
        if(entity.isPresent())return entity.get();
        else throw new VehiculeNotFoundException(id);

    }


}
