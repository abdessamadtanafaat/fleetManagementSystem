package com.system.gestionautomobile.controller;


import com.system.gestionautomobile.aspect.LogActivity;
import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.service.Trip.TripService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.Cacheable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/trip")
@AllArgsConstructor


public class TripController {


    private TripService tripService;
    @PostMapping()
    @LogActivity
    public ResponseEntity<Trip> saveTrip(@Valid @RequestBody Trip trip) {
        return new  ResponseEntity<>(tripService.saveTrip(trip) , HttpStatus.OK);
    }

    @GetMapping("/{tripId}")
    @LogActivity
    public ResponseEntity<Trip> getTripById(@PathVariable Long tripId){
        try {
            Trip trip = tripService.getTripById(tripId);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //affectation de vehicule à un voyage
    @LogActivity
    @PutMapping("/assign-vehicule/{tripId}/{vehiculeId}")
    public ResponseEntity<Vehicule> affectVehicule(@PathVariable long tripId ,@PathVariable long vehiculeId){
        return new ResponseEntity<>(tripService.assignVehiculeToTrip(tripId ,vehiculeId) ,HttpStatus.ACCEPTED);

    }
    //affectation du conducteur à un voyage
    @PutMapping("/assign-conducteur/{tripId}/{conducteurId}")
    @LogActivity
    public ResponseEntity<Conducteur> affectConducteur(@PathVariable Long tripId , @PathVariable Long conducteurId){
        return new ResponseEntity<>(tripService.assignConducteurToTrip(tripId , conducteurId),HttpStatus.ACCEPTED);

    }
    @GetMapping("/available-vehicules/{tripId}")
    @LogActivity
    public ResponseEntity<List<Vehicule>> getAvailableVehicules(@PathVariable Long tripId){
        return new ResponseEntity<>(tripService.getAvailableVehicules(tripId),HttpStatus.OK);

    }

    @GetMapping("/available-conducteurs/{tripId}")
    @LogActivity
    public ResponseEntity<List<Conducteur>> getAvailableConducteurs(@PathVariable Long tripId){
        return new ResponseEntity<>(tripService.getAvailableConducteurs(tripId),HttpStatus.OK);

    }
}
