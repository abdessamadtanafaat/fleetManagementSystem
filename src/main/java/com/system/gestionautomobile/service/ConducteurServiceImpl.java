package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.exception.EntityNotFoundException;
import com.system.gestionautomobile.repository.ConducteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConducteurServiceImpl implements ConducteurService {
    @Autowired
    private ConducteurRepository conducteurRepository;

    @Override
    public Conducteur saveConducteur(Conducteur conducteur){
        return conducteurRepository.save(conducteur);
    }
    public List<Conducteur> getAllConducteur(){
        return (List<Conducteur>)conducteurRepository.findAll();

    }

    public void deleteConducteur(Long id) {
        conducteurRepository.deleteById(id);
    }

    public Conducteur getConducteurById(long id){

        Optional<Conducteur> entity = conducteurRepository.findById(id);
        return unwrappedConducteur(entity , id);

    }

    public static Conducteur unwrappedConducteur(Optional<Conducteur> entity , long id){
        if(entity.isPresent())return entity.get();
        else throw new EntityNotFoundException(id ,Conducteur.class);
    }



}
