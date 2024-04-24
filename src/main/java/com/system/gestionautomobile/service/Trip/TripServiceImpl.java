package com.system.gestionautomobile.service.Trip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.gestionautomobile.caching.redis.RedisConfig;
import com.system.gestionautomobile.caching.redis.RedisUtils;
import com.system.gestionautomobile.entity.Conducteur;
import com.system.gestionautomobile.entity.Trip;
import com.system.gestionautomobile.entity.Vehicule;
import com.system.gestionautomobile.exception.*;
import com.system.gestionautomobile.repository.TripRepository;
import com.system.gestionautomobile.service.Conducteur.ConducteurService;
import com.system.gestionautomobile.service.Vehicule.VehiculeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;
    private ConducteurService conducteurService;
    private VehiculeService vehiculeService;
    private Jedis jedis;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, ConducteurService conducteurService, VehiculeService vehiculeService, Jedis jedis) {
        this.tripRepository = tripRepository;
        this.conducteurService = conducteurService;
        this.vehiculeService = vehiculeService;
        this.jedis = jedis;
    }

    public void isTripValid(Trip trip) {
        if (trip.getDateArrivePrevue().isEqual(trip.getDateDebut())) {
            if (trip.getHeureDepart().isAfter(trip.getHeureArrivePrevue())) {
                throw new InvalidDateOrderException(trip.getDateArrivePrevue(), trip.getDateDebut(), trip.getHeureArrivePrevue(), trip.getHeureDepart());
            }
        } else if (trip.getDateArrivePrevue().isBefore(trip.getDateDebut())) {

            throw new InvalidDateOrderException(trip.getDateArrivePrevue(), trip.getDateDebut(), trip.getHeureArrivePrevue(), trip.getHeureDepart());
        }
    }

    @Override
    public Trip saveTrip(Trip trip) throws InvalidDateOrderException {
        isTripValid(trip);
        return tripRepository.save(trip);
    }

    @Override
    public Trip getTripById(Long tripId) {
        Trip trip = new Trip();

        try {
            String tripData = RedisUtils.get(jedis,"Trip_id : " + tripId);
            if (tripData != null) {
                trip = RedisUtils.deserializeObject(tripData, Trip.class);
                System.out.println("---From the Cache---");
            } else {
                Optional<Trip> entity = tripRepository.findById(tripId);
                if (entity.isPresent()) {
                    trip = entity.get();
                    RedisUtils.setex(jedis, "Trip_id : " + tripId, 3600, trip);
                    System.out.println("---From the Database---");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            RedisUtils.close(jedis);
        }
        return trip;
    }

/*    private String serializeTrip(Trip trip) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            return objectMapper.writeValueAsString(trip);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }*/

/*    private Trip parseTrip(String tripData) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            return objectMapper.readValue(tripData, Trip.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }*/
    @Override
    public Vehicule assignVehiculeToTrip(long tripId , long vehiculeId) {
        Trip trip =getTripById(tripId);
        Vehicule vehicule  = vehiculeService.getVehiculeById(vehiculeId);
        trip.setVehicule(vehicule);
        vehicule.getTrips().add(trip);
        vehicule.setDisponible(false);
        return vehiculeService.saveVehicule(vehicule);
    }


    @Override
    public Conducteur assignConducteurToTrip(long tripId ,long conducteurId ) {
        Trip trip = getTripById(tripId);
        Conducteur conducteur = conducteurService.getConducteurById(conducteurId);
        trip.setConducteur(conducteur);
        conducteur.getTrips().add(trip);
        conducteur.setDisponible(false);
        return conducteurService.saveSimple(conducteur);
    }

    @Override
    public List<Vehicule> getAvailableVehicules(long tripId) {
        Trip trip =getTripById(tripId);
        return vehiculeService.getAvailableVehicules(trip);
    }

    @Override
    //@Cacheable(value = "myCache", key = "'trip_' +#tripId", cacheManager = "cacheManager")
    public List<Conducteur> getAvailableConducteurs(long tripId) {
        Trip trip = getTripById(tripId);
        return conducteurService.getAvailableConducteurs(trip);
    }


    public static Trip unwrappTrip(Optional<Trip> entity, long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Trip.class);

    }





}
