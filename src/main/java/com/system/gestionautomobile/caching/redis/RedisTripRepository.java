/*
package com.system.gestionautomobile.caching.redis.redisConfig;

import com.system.gestionautomobile.entity.Trip;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedisTripRepository extends CrudRepository<Trip, Long> {

    @Override
    @Cacheable(value = "tripCache", key = "#id")
    Optional<Trip> findById(Long id);

    @Override
    @CachePut(value = "tripCache", key = "#result.id")
    <S extends Trip> S save(S entity);

    @Override
    @CacheEvict(value = "tripCache", key = "#id")
    void deleteById(Long id);
}
*/
