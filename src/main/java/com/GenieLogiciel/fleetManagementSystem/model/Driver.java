package com.GenieLogiciel.fleetManagementSystem.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name="_driver")
public class Driver {
    @Id
    private String matricule;
    private String name;
    @OneToMany(mappedBy = "driver")
    @JsonManagedReference
    private List<Trip> trips = new ArrayList<>();

}
