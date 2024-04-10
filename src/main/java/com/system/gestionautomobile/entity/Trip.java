package com.system.gestionautomobile.entity;


import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.lettuce.core.dynamic.annotation.Key;
import jakarta.persistence.*;

import jakarta.validation.constraints.Future;
import lombok.*;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="trip")
public class Trip implements Serializable {

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
