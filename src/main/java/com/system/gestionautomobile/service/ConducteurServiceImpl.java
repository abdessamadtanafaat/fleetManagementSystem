package com.system.gestionautomobile.service;

import com.system.gestionautomobile.repository.ConducteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConducteurServiceImpl {
    @Autowired
    private ConducteurRepository conducteurRepository;

}
