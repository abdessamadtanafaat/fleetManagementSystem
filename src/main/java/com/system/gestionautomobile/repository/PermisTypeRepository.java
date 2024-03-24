package com.system.gestionautomobile.repository;

import com.system.gestionautomobile.entity.PermisType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisTypeRepository extends CrudRepository<PermisType , Long> {
}
