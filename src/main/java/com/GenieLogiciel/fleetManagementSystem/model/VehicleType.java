package com.GenieLogiciel.fleetManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Table;
import lombok.ToString;

@ToString
public enum VehicleType {
    CAR,
    BUS,
    FOURGONNETTE,
    TRUCK

}
