package com.system.gestionautomobile.controller;


import com.system.gestionautomobile.entity.Trip;
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
    @PostMapping
    public ResponseEntity<Trip> saveTrip(@Valid @RequestBody Trip trip ){
        return new ResponseEntity<>(tripService.saveTrip(trip) , HttpStatus.CREATED);
    }
    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long tripId){
        return new ResponseEntity<>(tripService.getTripById(tripId),HttpStatus.OK );
    }
    //affectation de vehicule à un voyage
    /*@PutMapping("/{tripId}")
    public ResponseEntity<> affectVehicule(@PathVariable Long vehiculeId){
        return new ResponseEntity<>();

    }*/
    //affectation du conducteur à un voyage
    @PutMapping("/{tripId}")
    public ResponseEntity<Trip> affectConducteur(@PathVariable Long tripId){

        return new ResponseEntity<>(HttpStatus.ACCEPTED) ;

    }







}