package com.system.gestionautomobile.service;

import com.system.gestionautomobile.entity.*;
import com.system.gestionautomobile.exception.EntityNotFoundException;
import com.system.gestionautomobile.repository.ConducteurRepository;
import com.system.gestionautomobile.repository.PermisRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConducteurServiceImpl implements ConducteurService {
    private ConducteurRepository conducteurRepository;
    private PermisService permisService;




    @Override
    public Conducteur saveConducteur(Conducteur conducteur){
        Permis permis = conducteur.getPermis();
        Set<PermisType> permisTypes = permis.getPermisType();
        permis.setPermisType(null);

        permisService.savePermis(permis);
        permisTypes = permisService.savePermisTypes( permis,permisTypes);
        permis.setPermisType(permisTypes);
        permisService.savePermis(permis);
        return conducteurRepository.save(conducteur) ;
    }

    @Override
    public void deleteConducteur(long conducteurId) {
        conducteurRepository.deleteById(conducteurId);
    }
    @Override
    public List<Conducteur> getAllConducteurs() {
        return (List<Conducteur>)conducteurRepository.findAll();
    }

    @Transactional
    @Cacheable(value = "availableDrivers", key = "'allDrivers'")
    @Override
    public List<Conducteur> getAvailableConducteurs(Trip trip) {

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }


        //check permis type
        PermisCategorie permisCategorie= VehiculeToPermisCategorieService.getPermisCategorie(trip.getTypeVehicule());
        List<Conducteur> conducteurs = conducteurRepository.findByPermisTypeCategorie(permisCategorie);
        //implements stream
        return conducteurs.
                stream().
                filter(
                        conducteur->isConducteurAvailable(conducteur
                                ,trip.getDateDebut() , trip.getDateArrivePrevue() ,
                                trip.getHeureDepart() , trip.getHeureArrivePrevue()))
                .collect(Collectors.toList());

    }

    @Override
    public Conducteur saveSimple(Conducteur conducteur) {
        return conducteurRepository.save(conducteur);
    }

    public boolean isConducteurAvailable(Conducteur conducteur , LocalDate dateDebut , LocalDate dateArrive , LocalTime heureDepart , LocalTime heureArrive){
        Set<Trip> trips = conducteur.getTrips();
        return trips.stream().filter(trip -> compareToNewTrip(trip, dateDebut, dateArrive, heureDepart, heureArrive)).count() == 0;

    }
    public boolean compareToNewTrip(Trip trip , LocalDate dateDebut , LocalDate datePrevue , LocalTime heureDepart , LocalTime heureArrive){
        LocalDate tripDateDebut = trip.getDateDebut();
        LocalDate tripDateArrivePrevue = trip.getDateArrivePrevue();
        LocalTime tripHeureDepart = trip.getHeureDepart();
        LocalTime tripHeureArrive = trip.getHeureArrivePrevue();
        boolean datesOverlap = !(datePrevue.isBefore(tripDateDebut) || dateDebut.isAfter(tripDateArrivePrevue));
        if (datesOverlap) {
            boolean timesOverlap = !(heureArrive.isBefore(tripHeureDepart) || heureDepart.isAfter(tripHeureArrive));
            if (!timesOverlap) {
                Duration totalDurationWithinDateRange = trip.getConducteur().getTrips().stream()
                        .filter(t -> !(t.getDateArrivePrevue().isBefore(dateDebut) || t.getDateDebut().isAfter(datePrevue)))
                        .map(t -> Duration.between(t.getHeureDepart(), t.getHeureArrivePrevue()))

                        .reduce(Duration.ZERO, Duration::plus);
                Duration newTripDuration = Duration.between(heureDepart, heureArrive);
                Duration totalDurationWithNewTrip = totalDurationWithinDateRange.plus(newTripDuration);
                return totalDurationWithNewTrip.toHours() >= 8;
            }
            return true;
        }
        return true;
    }
    public Conducteur getConducteurById(long id){
        Optional<Conducteur> entity = conducteurRepository.findById(id);
        return unwrappedConducteur(entity , id);
    }
    public static Conducteur unwrappedConducteur(Optional<Conducteur> entity , long id){
        if(entity.isPresent())return entity.get();
        else throw new EntityNotFoundException(id ,Conducteur.class);
    }



}
