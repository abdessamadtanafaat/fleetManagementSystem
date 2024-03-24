package com.system.gestionautomobile.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

@Entity
@Table(name="permistype")
@Getter
@Setter
public class PermisType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @ManyToOne(optional = false ,cascade =  CascadeType.ALL )
    @JoinColumn(name="permis_id" ,  referencedColumnName = "id")
    @JsonIgnore

    private Permis permis;
    @Enumerated(EnumType.STRING)
    private PermisCategorie permisCategorie ;
    private LocalDate dateDelivrance ;
}
