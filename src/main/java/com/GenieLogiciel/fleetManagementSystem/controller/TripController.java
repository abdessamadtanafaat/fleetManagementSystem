package com.GenieLogiciel.fleetManagementSystem.controller;
import com.GenieLogiciel.fleetManagementSystem.model.Trip;
import com.GenieLogiciel.fleetManagementSystem.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {
    private final TripService tripService;
    //private final ItripMapper tripMapper;

    @Autowired
    public TripController(TripService tripService)
    {
        this.tripService = tripService;
        //this.tripMapper = tripMapper;

    }
    @PostMapping("/trips")
    public ResponseEntity<?> createTrip(@RequestBody Trip trip){
        try{
            Trip savedTrip = tripService.saveTrip(trip);
            return new ResponseEntity<Trip>(savedTrip, HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Failed to create trip: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }



}
