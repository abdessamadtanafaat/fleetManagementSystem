package com.system.gestionautomobile.service.Vehicule;

import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.exception.EntityNotFoundException;
import com.system.gestionautomobile.repository.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements VehiculeService {

    private VehiculeRepository vehiculeRepository;
    public Vehicule saveVehicule(Vehicule vehicule ){
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void deleteVehicule(long vehiculeId) {
        vehiculeRepository.deleteById(vehiculeId);
    }

    @Override
    public Vehicule getVehiculeById(long vehicleId) {
        Optional<Vehicule> entity = vehiculeRepository.findById(vehicleId);
        return unwrappVehicule(entity, vehicleId);

    }

    @Override
    public List<Vehicule> getAvailableVehicules(Trip trip) {
        List<Vehicule> vehicules = vehiculeRepository.findVehiculeByDisponibleAndVehiculeType(true , trip.getTypeVehicule());
        return vehicules;
    }



    public static  Vehicule unwrappVehicule(Optional<Vehicule> entity , long id){
        if(entity.isPresent())return entity.get();
        else throw new EntityNotFoundException(id , Vehicule.class);

    }


}
