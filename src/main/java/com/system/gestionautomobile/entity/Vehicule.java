package com.system.gestionautomobile.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="vehicule")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String marque ;
    @Enumerated(EnumType.STRING)
    private VehiculeType vehiculeType;
    private float kilometrage;
    @Enumerated(EnumType.STRING)
    private PermisCategorie permisCategorie;
    private boolean disponible ;
    private String equipements;

    @OneToMany(mappedBy = "vehicule")
    @JsonIgnore
    private Set<Trip> trips;


}
