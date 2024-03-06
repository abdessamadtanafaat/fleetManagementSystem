package com.GenieLogiciel.fleetManagementSystem.controller;
import com.GenieLogiciel.fleetManagementSystem.Exception.DriverNotFoundException;
import com.GenieLogiciel.fleetManagementSystem.Exception.InvalidDateOrderException;
import com.GenieLogiciel.fleetManagementSystem.Exception.TripServiceException;
import com.GenieLogiciel.fleetManagementSystem.Exception.UnsupportedVehicleTypeException;
import com.GenieLogiciel.fleetManagementSystem.model.Trip;
import com.GenieLogiciel.fleetManagementSystem.service.TripService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class TripController {

    @Autowired
    private final TripService tripService;

    @PostMapping("/trips")
    public ResponseEntity<?> createTrip(@RequestBody Trip trip) {
        try {
            Trip savedTrip = tripService.saveTrip(trip);
            return new ResponseEntity<Trip>(savedTrip, HttpStatus.CREATED);
        }
        catch (InvalidDateOrderException | UnsupportedVehicleTypeException | DriverNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (TripServiceException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
