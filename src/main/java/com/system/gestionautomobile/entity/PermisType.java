package com.system.gestionautomobile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="permistype")
@Getter
@Setter
public class PermisType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @ManyToOne(optional = false)
    @JoinColumn(name="permis_id")
    private Permis permis;
    private PermisCategorie permisCategorie ;
    private LocalDate dateDelivrance ;
}
