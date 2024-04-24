package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.VehiculeType;
import com.system.gestionautomobile.exception.InvalidDateOrderException;
import com.system.gestionautomobile.repository.TripRepository;
import com.system.gestionautomobile.service.Trip.TripService;
import com.system.gestionautomobile.service.Trip.TripServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;

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
        TripService tripServiceMock = Mockito.mock(TripService.class);

        Trip validTrip = createValidTrip();

        when(tripServiceMock.saveTrip(validTrip)).thenReturn(validTrip);

        Trip savedTrip = tripServiceMock.saveTrip(validTrip);

        verify(tripServiceMock, times(1)).saveTrip(validTrip);
    }

    @Test
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