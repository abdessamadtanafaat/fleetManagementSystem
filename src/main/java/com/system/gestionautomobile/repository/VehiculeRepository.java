package com.system.gestionautomobile.repository;

import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.entity.VehiculeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends CrudRepository<Vehicule, Long> {
    List<Vehicule> findVehiculeByDisponibleAndVehiculeType(boolean disponible , VehiculeType vehiculetype);
}
