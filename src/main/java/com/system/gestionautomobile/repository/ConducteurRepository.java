package com.system.gestionautomobile.repository;

import com.system.gestionautomobile.entity.Conducteur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConducteurRepository extends CrudRepository<Conducteur , Long> {
    
}
