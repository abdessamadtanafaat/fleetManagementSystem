package com.GenieLogiciel.fleetManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name="_trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departure;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private int numberOfPassenger;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    @ManyToOne()
    @JoinColumn(name ="matricule")
    @JsonBackReference
    private Driver driver;
}
