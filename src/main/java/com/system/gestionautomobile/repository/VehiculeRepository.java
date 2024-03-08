package com.system.gestionautomobile.repository;

import com.system.gestionautomobile.entity.Vehicule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends CrudRepository<Vehicule, Long> {
}
