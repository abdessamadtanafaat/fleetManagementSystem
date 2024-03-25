package com.system.gestionautomobile.repository;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.PermisCategorie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConducteurRepository extends CrudRepository<Conducteur , Long> {
    @Query("SELECT DISTINCT c FROM Conducteur c " +
            "JOIN c.permis p " +
            "JOIN p.permisType pt " +
            "WHERE pt.permisCategorie = :categorie")
    List<Conducteur> findByPermisTypeCategorie(@Param("categorie") PermisCategorie categorie);
    
}
