package com.system.gestionautomobile.controller;

import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.service.VehiculeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicule")
public class VehiculeController {
    @Autowired
    private VehiculeService vehiculeService ;
    @PostMapping
    public ResponseEntity<Vehicule> saveVehicule(@Valid @RequestBody Vehicule vehicule){
        return new ResponseEntity<>(vehiculeService.saveVehicule(vehicule), HttpStatus.CREATED);
    }
    @GetMapping("/{vehiculeId}")
    public ResponseEntity<Vehicule> getTripById(@PathVariable Long vehiculeId){
        return new ResponseEntity<>(vehiculeService.getVehiculeById(vehiculeId),HttpStatus.OK );
    }


}
