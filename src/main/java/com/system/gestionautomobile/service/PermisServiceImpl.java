package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Permis;
import com.system.gestionautomobile.entity.PermisType;
import com.system.gestionautomobile.repository.PermisRepository;
import com.system.gestionautomobile.repository.PermisTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PermisServiceImpl implements  PermisService{
    PermisRepository permisRepository;
    PermisTypeRepository permisTypeRepository;
    @Override
    public Permis savePermis(Permis permis) {
        Permis savedPermis =  permisRepository.save(permis);
        return savedPermis ;
    }

    @Override
    public Set<PermisType> savePermisTypes(Permis permis , Set<PermisType> permisTypes) {
        Set<PermisType> savedPermisTypes = new HashSet<>();
        for(PermisType permisType :permisTypes){
            permisType.setPermis(permis);
            savedPermisTypes.add(permisTypeRepository.save(permisType));

        }
        return savedPermisTypes  ;
    }


}
