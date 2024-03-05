package com.GenieLogiciel.fleetManagementSystem.repository;

import com.GenieLogiciel.fleetManagementSystem.model.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
}
