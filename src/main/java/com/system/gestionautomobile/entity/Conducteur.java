package com.system.gestionautomobile.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="conducteur")
@Builder
@Getter
@Setter
public class Conducteur {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    private String nom ;
    private String prenom ;
    private String matricule;
    private LocalDate dateNaissance;
    private String cin ;
    @OneToMany(mappedBy = "conducteur")
    @JsonIgnore
    private Set<Trip> trips ;
    @OneToOne(optional=false , cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name="permis_id")
    private Permis permis;

}
