package com.system.gestionautomobile.repository;

import com.system.gestionautomobile.entity.Trip;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableRedisRepositories
public interface TripRepository extends CrudRepository<Trip, Long> {
}
