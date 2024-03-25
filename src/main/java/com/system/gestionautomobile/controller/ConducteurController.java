package com.system.gestionautomobile.controller;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.service.ConducteurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/conducteur")
@AllArgsConstructor
public class ConducteurController {

    private ConducteurService conducteurService;


    @PostMapping
    public ResponseEntity<Conducteur> createConducteur(@Valid @RequestBody Conducteur conducteur){
        return new ResponseEntity<>(conducteurService.saveConducteur(conducteur), HttpStatus.CREATED);
    }


}
