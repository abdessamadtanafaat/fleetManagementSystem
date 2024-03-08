package com.system.gestionautomobile.repository;

import com.system.gestionautomobile.entity.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {


}
