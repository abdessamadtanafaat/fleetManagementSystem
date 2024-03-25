package com.system.gestionautomobile.controller;

import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.service.VehiculeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController {
    private VehiculeService vehiculeService ;
    @PostMapping()
    public ResponseEntity<Vehicule> saveVehicle(@Valid @RequestBody Vehicule vehicule){
        return new ResponseEntity<>(vehiculeService.saveVehicule(vehicule), HttpStatus.CREATED);
    }
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicule> getTripById(@PathVariable Long vehicleId){
        return new ResponseEntity<>(vehiculeService.getVehiculeById(vehicleId),HttpStatus.OK );
    }


}
