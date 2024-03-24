package com.system.gestionautomobile.entity;


import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;

import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Future(message="La date que vous avez passée est dans la passé !!!")
    private LocalDate dateDebut;
    private LocalTime heureDepart;
    //@Future(message="La date que vous avez passée est dans la passé !!!")
    private LocalDate dateArrivePrevue;
    private LocalTime heureArrivePrevue;
    private String depart;
    private String destination ;
    @Enumerated(EnumType.STRING)
    private VehiculeType typeVehicule;
    private int nbPassagers;
    private String autreDetails;

    @ManyToOne
    @JoinColumn(name="vehicule_id")
    private Vehicule vehicule;
    @ManyToOne
    @JoinColumn(name="conducteur_id")
    private Conducteur conducteur;
}
