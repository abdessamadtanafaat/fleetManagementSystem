package com.system.gestionautomobile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="vehicule")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String marque ;
    @Enumerated(EnumType.STRING)
    private VehiculeType vehiculeType;
    private float kilometrage;
    @Enumerated(EnumType.STRING)
    private PermisType permisType;
    private boolean disponible ;
    private String equipements;

    @OneToMany(mappedBy = "vehicule")
    private Set<Trip> trips;


}
