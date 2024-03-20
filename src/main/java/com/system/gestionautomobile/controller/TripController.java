package com.system.gestionautomobile.controller;


import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.exception.InvalidDateOrderException;
import com.system.gestionautomobile.service.TripService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
@AllArgsConstructor
public class TripController {

    private TripService tripService;
    @PostMapping()
    public ResponseEntity<Trip> saveTrip(@Valid @RequestBody Trip trip) {
        return new  ResponseEntity<>(tripService.saveTrip(trip) , HttpStatus.OK);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long tripId){
        return new ResponseEntity<>(tripService.getTripById(tripId),HttpStatus.OK );
    }
    //affectation de vehicule à un voyage
    @PutMapping("/assign-vehicule/{tripId}")
    public ResponseEntity<Vehicule> affectVehicule(@PathVariable long tripId){
        return new ResponseEntity<>(tripService.assignVehiculeToTrip(tripId) ,HttpStatus.ACCEPTED);

    }
    //affectation du conducteur à un voyage
    @PutMapping("/assign-conducteur/{tripId}")
    public ResponseEntity<Conducteur> affectConducteur(@PathVariable Long tripId){
        return new ResponseEntity<>(tripService.assignConducteurToTrip(tripId),HttpStatus.ACCEPTED);

    }

}
