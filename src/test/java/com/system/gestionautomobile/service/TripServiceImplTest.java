//import com.system.gestionautomobile.entity.Trip;
//import com.system.gestionautomobile.repository.ConducteurRepository;
//import com.system.gestionautomobile.repository.TripRepository;
//import com.system.gestionautomobile.service.TripService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//
//class TripServiceTest {
//
//    @Mock
//    private TripRepository tripRepository;
//
//    @Mock
//    private ConducteurRepository conducteurRepository;
//
//    private TripService tripService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        tripService = new TripService(tripRepository, driverRepository);
//    }
//
//    @Test
//    public void testSaveTripSuccess() {
//        TripRepository tripRepository = Mockito.mock(TripRepository.class);
//        ConducteurRepository conducteurRepository = Mockito.mock(ConducteurRepository.class);
////        TripService tripService = new TripService(tripRepository, conducteurRepository);
//
//        Trip validTrip = createValidTripWithCondcuteur();
//        when(ConducteurRepository.existsById(validTrip.getConducteur().getMatricule())).thenReturn(true); // Mock Conducteur existence check
//        when(tripRepository.save(validTrip)).thenReturn(validTrip); // Mock trip save operation
//
//        // Invoke saveTrip method
//        Trip savedTrip = tripService.saveTrip(validTrip);
//
//        // Verify
//        assertNotNull(savedTrip);
//        assertEquals(validTrip, savedTrip);
//
//    }
//
//    @Test
//    public void testSaveTripException() {
//        // Arrange
//        Trip trip = createValidTripWithConducteur();
//        // Mock necessary repository methods
//        when(ConducteurRepository.existsById(trip.getDriver().getMatricule())).thenReturn(true);
//        when(tripRepository.save(trip)).thenThrow(new RuntimeException()); // Simulate an exception during save
//
//        // Act & Assert
//        assertThrows(TripServiceException.class, () -> tripService.saveTrip(trip));
//    }
//
//    @Test
//    public void testSaveTripDriverNotFound() {
//        // Arrange
//        Trip trip = createTripWithNonExistingDriver(); // Create a trip with a driver that doesn't exist
//        when(driverRepository.existsById(trip.getDriver().getMatricule())).thenReturn(false);
//
//        // Act & Assert
//        assertThrows(DriverNotFoundException.class, () -> tripService.saveTrip(trip));
//    }
//
//    @Test
//    public void testSaveTripInvalidDateOrder() {
//        // Arrange
//        Trip trip = createInvalidDateOrderTrip(); // Create a trip with invalid date order
//        when(driverRepository.existsById(trip.getDriver().getMatricule())).thenReturn(true);
//
//        // Act & Assert
//        assertThrows(InvalidDateOrderException.class, () -> tripService.saveTrip(trip));
//    }
//
//    private Trip createInvalidDateOrderTrip() {
//        Trip trip = new Trip();
//        Driver driver = new Driver();
//        driver.setMatricule("A1234");
//        trip.setDriver(driver);
//        trip.setDepartureDate(LocalDate.now());
//        trip.setArrivalDate(LocalDate.now());
//        trip.setDepartureTime(LocalTime.of(12, 0)); // Set departure time after arrival time
//        trip.setArrivalTime(LocalTime.of(10, 0));
//        return trip;
//    }
//
//    private Trip createTripWithNonExistingDriver() {
//        Trip trip = new Trip();
//        Driver driver = new Driver();
//        driver.setMatricule("A23454");
//        trip.setDriver(driver);
//        return trip;
//    }
//    private Trip createValidTripWithDriver() {
//        Trip trip = new Trip();
//        Driver driver = new Driver();
//        driver.setMatricule("validMatricule");
//        trip.setDepartureDate(LocalDate.parse("2024-02-28"));
//        trip.setDeparture("Agadir");
//        trip.setArrivalDate(LocalDate.parse("2024-02-29"));
//        trip.setDestination("Marrakech");
//        trip.setDepartureTime(LocalTime.parse("11:00:00"));
//        trip.setArrivalTime(LocalTime.parse("11:00:00"));
//        trip.setNumberOfPassenger(10);
//        trip.setVehicleType(VehicleType.CAR);
//        trip.setDriver(driver);
//        return trip;
//
//
//
//    }
//
//
//}