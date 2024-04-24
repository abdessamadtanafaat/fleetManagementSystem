package com.system.gestionautomobile.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="permis")
@Getter
@Setter
public class Permis  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String permisNum;
    @OneToMany( mappedBy = "permis" )

    private Set<PermisType> permisType;
    private LocalDate finDeValidite ;
    @OneToOne(optional=false , mappedBy = "permis")
    @JsonIgnore
    private Conducteur conducteur;
}
