package com.GenieLogiciel.fleetManagementSystem.repository;

import com.GenieLogiciel.fleetManagementSystem.model.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Driver, String> {
}
