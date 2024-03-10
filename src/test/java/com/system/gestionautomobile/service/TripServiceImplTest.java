package com.system.gestionautomobile.service;

<<<<<<< HEAD
import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.VehiculeType;
import com.system.gestionautomobile.exception.EntityNotFoundException;
import com.system.gestionautomobile.exception.InvalidDateOrderException;
import com.system.gestionautomobile.exception.TripServiceException;
import com.system.gestionautomobile.repository.ConducteurRepository;
import com.system.gestionautomobile.repository.TripRepository;
import com.system.gestionautomobile.service.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private ConducteurRepository conducteurRepository;

    private TripService tripService;
=======
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
>>>>>>> d3c15cc3662f9ca38da23124591f2d61aaf6a03a

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
<<<<<<< HEAD
//        tripService = new TripService(tripRepository, conducteurRepository);
=======

>>>>>>> d3c15cc3662f9ca38da23124591f2d61aaf6a03a
    }

    @Test
    public void testSaveTripSuccess() {
<<<<<<< HEAD
        TripRepository tripRepository = Mockito.mock(TripRepository.class);
        ConducteurRepository conducteurRepository = Mockito.mock(ConducteurRepository.class);
//        TripService tripService = new TripService(tripRepository, conducteurRepository);

        Trip validTrip = createValidTripWithConducteur();
        when(conducteurRepository.existsById(Long.valueOf(validTrip.getConducteur().getMatricule()))).thenReturn(true); // Mock Conducteur existence check
        when(tripRepository.save(validTrip)).thenReturn(validTrip); // Mock trip save operation

        // Invoke saveTrip method
        Trip savedTrip = tripService.saveTrip(validTrip);

        // Verify
        assertNotNull(savedTrip);
        assertEquals(validTrip, savedTrip);
=======
        Trip validTrip = createValidTrip();

        // Mock trip save operation
        when(tripRepository.save(validTrip)).thenReturn(validTrip);

        // Invoke saveTrip method
        ResponseEntity<?> responseEntity = tripService.saveTrip(validTrip);

        // Verify
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ResponseEntity.ok(validTrip), responseEntity);
>>>>>>> d3c15cc3662f9ca38da23124591f2d61aaf6a03a

    }

    @Test
<<<<<<< HEAD
    public void testSaveTripException() {
        // Arrange
        Trip trip = createValidTripWithConducteur();
        // Mock necessary repository methods
        when(conducteurRepository.existsById(Long.valueOf(trip.getConducteur().getMatricule()))).thenReturn(true);
        when(tripRepository.save(trip)).thenThrow(new RuntimeException()); // Simulate an exception during save

        // Act & Assert
        assertThrows(TripServiceException.class, () -> tripService.saveTrip(trip));
    }

    @Test
    public void testSaveTripDriverNotFound() {
        // Arrange
        Trip trip = createTripWithNonExistingConducteur(); // Create a trip with a driver that doesn't exist
        when(conducteurRepository.existsById(Long.valueOf(trip.getConducteur().getMatricule()))).thenReturn(false);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> tripService.saveTrip(trip));
    }

    @Test
    public void testSaveTripInvalidDateOrder() {
        // Arrange
        Trip trip = createInvalidDateOrderTrip(); // Create a trip with invalid date order
        when(conducteurRepository.existsById(Long.valueOf(trip.getConducteur().getMatricule()))).thenReturn(true);

        // Act & Assert
        assertThrows(InvalidDateOrderException.class, () -> tripService.saveTrip(trip));
    }

    private Trip createInvalidDateOrderTrip() {
        Trip trip = new Trip();
        Conducteur conducteur=  new Conducteur();
        conducteur.setMatricule("A1234");
        trip.setConducteur(conducteur);
        trip.setDateDebut(LocalDate.now());
        trip.setDateArrivePrevue(LocalDate.now());
        trip.setHeureDepart(LocalTime.of(12, 0)); // Set departure time after arrival time
        trip.setHeureArrivePrevue(LocalTime.of(10, 0));
        return trip;
    }

    private Trip createTripWithNonExistingConducteur() {
        Trip trip = new Trip();
        Conducteur conducteur = new Conducteur();
        conducteur.setMatricule("A23454");
        trip.setConducteur(conducteur);
        return trip;
    }
    private Trip createValidTripWithConducteur() {
        Trip trip = new Trip();
        Conducteur conducteur = new Conducteur();
        conducteur.setMatricule("validMatricule");
        trip.setDateDebut(LocalDate.parse("2024-02-28"));
        trip.setDepart("Agadir");
        trip.setDateArrivePrevue(LocalDate.parse("2024-02-29"));
        trip.setDestination("Marrakech");
        trip.setHeureDepart(LocalTime.parse("11:00:00"));
        trip.setHeureArrivePrevue(LocalTime.parse("11:00:00"));
        trip.setNbPassagers(10);
        trip.setTypeVehicule(VehiculeType.CAR);
        trip.setConducteur(conducteur);
        return trip;



    }


}
=======
    public void testSaveTripInvalidDateOrder() {
        Trip invalidTrip = createInvalidDTrip(); // Create a trip with invalid date order

        // Act & Assert
        assertThrows(InvalidDateOrderException.class, () -> {
            tripService.saveTrip(invalidTrip);
        });


    }
    
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

>>>>>>> d3c15cc3662f9ca38da23124591f2d61aaf6a03a
