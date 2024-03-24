package com.system.gestionautomobile.repository;

import com.system.gestionautomobile.entity.Permis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisRepository extends CrudRepository<Permis, Long> {
}
