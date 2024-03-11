package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.VehiculeType;
import com.system.gestionautomobile.exception.InvalidDateOrderException;
import com.system.gestionautomobile.repository.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TripServiceImplTest {

    @Mock
    private TripRepository tripRepository;
    @InjectMocks
    private TripServiceImpl tripService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testSaveTripSuccess() {
        Trip validTrip = createValidTrip();

        // Mock trip save operation
        when(tripRepository.save(validTrip)).thenReturn(validTrip);
        verify(tripService , times(1)).saveTrip(validTrip);

//        // Invoke saveTrip method
//        ResponseEntity<?> responseEntity = tripService.saveTrip(validTrip);
//
//        // Verify
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(ResponseEntity.ok(validTrip), responseEntity);

    }

    @Test
    public void testSaveTripInvalidDateOrder() {
        Trip invalidTrip = createInvalidDTrip(); // Create a trip with invalid date order

        // Act & Assert
        assertThrows(InvalidDateOrderException.class, () -> {
            tripService.saveTrip(invalidTrip);
        });


    }


/*    @Test
    public void testSaveTripException() {
        // Arrange
        Trip trip = createValidTripWithConducteur();
        // Mock necessary repository methods
        when(ConducteurRepository.existsById(trip.getDriver().getMatricule())).thenReturn(true);
        when(tripRepository.save(trip)).thenThrow(new RuntimeException()); // Simulate an exception during save
        // Act & Assert
        assertThrows(TripServiceException.class, () -> tripService.saveTrip(trip));
    }*/

 /*   @Test
    public void testSaveTripDriverNotFound() {
        // Arrange
        Trip trip = createTripWithNonExistingDriver(); // Create a trip with a driver that doesn't exist
        when(driverRepository.existsById(trip.getDriver().getMatricule())).thenReturn(false);
        // Act & Assert
        assertThrows(DriverNotFoundException.class, () -> tripService.saveTrip(trip));
    }
    }*/

    private Trip createValidTrip() {
        Trip trip = new Trip();
        trip.setDepart("agadir");
        trip.setDestination("Laayoune");
        trip.setDateDebut(LocalDate.now());
        trip.setDateArrivePrevue(LocalDate.now());
        trip.setHeureDepart(LocalTime.of(9, 0));
        trip.setHeureArrivePrevue(LocalTime.of(10, 0));
        trip.setNbPassagers(12);
        trip.setTypeVehicule(VehiculeType.CAR);
        trip.setAutreDetails("string");
        return trip;
    }
    private Trip createInvalidDTrip() {
        Trip trip = new Trip();
        trip.setDepart("agadir");
        trip.setDestination("Laayoune");
        trip.setDateDebut(LocalDate.now());
        trip.setDateArrivePrevue(LocalDate.now());
        trip.setHeureDepart(LocalTime.of(11, 0));
        trip.setHeureArrivePrevue(LocalTime.of(10, 0));
        trip.setNbPassagers(12);
        trip.setTypeVehicule(VehiculeType.CAR);
        trip.setAutreDetails("string");
        return trip;
    }


}