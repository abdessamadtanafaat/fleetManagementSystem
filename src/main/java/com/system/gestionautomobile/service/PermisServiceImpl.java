package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Permis;
import com.system.gestionautomobile.repository.PermisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermisServiceImpl implements  PermisService{
    PermisRepository permisRepository;
    @Override
    public Permis savePermis(Permis permis) {
        return  permisRepository.save(permis);
    }
}
