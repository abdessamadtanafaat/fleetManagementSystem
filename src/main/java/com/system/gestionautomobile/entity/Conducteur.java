package com.system.gestionautomobile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="conducteur")
@Builder
@Data
public class Conducteur {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    private String nom ;
    private String prenom ;
    private String matricule;
    private LocalDate dateNaissance;
    private String cin ;
    private PermisType typePermisRequis;

    @OneToMany(mappedBy = "conducteur")
    private Set<Trip> trips ;


}
