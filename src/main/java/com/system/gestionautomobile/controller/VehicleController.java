package com.system.gestionautomobile.controller;

import com.system.gestionautomobile.aspect.LogActivity;
import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.service.Vehicule.VehiculeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/vehicle")
public class VehicleController {
    private VehiculeService vehiculeService ;
    @PostMapping()
    @LogActivity
    public ResponseEntity<Vehicule> saveVehicle(@Valid @RequestBody Vehicule vehicule){
        return new ResponseEntity<>(vehiculeService.saveVehicule(vehicule), HttpStatus.CREATED);
    }
    @GetMapping("/{vehicleId}")
    @LogActivity
    public ResponseEntity<Vehicule> getTripById(@PathVariable Long vehicleId){
        return new ResponseEntity<>(vehiculeService.getVehiculeById(vehicleId),HttpStatus.OK );
    }


}
