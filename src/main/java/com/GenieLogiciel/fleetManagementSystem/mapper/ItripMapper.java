/*
package com.GenieLogiciel.fleetManagementSystem.mapper;

import com.GenieLogiciel.fleetManagementSystem.dto.request.TripRequestDTO;
import com.GenieLogiciel.fleetManagementSystem.model.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ItripMapper {

    ItripMapper INSTANCE = Mappers.getMapper(ItripMapper.class);

    @Mappings({
            @Mapping(source = "departure", target = "departure"),
            @Mapping(source = "destination", target = "destination"),
            @Mapping(source = "departureDate", target = "departureDate"),
            @Mapping(source = "departureTime", target = "departureTime"),
            @Mapping(source = "arrivalDate", target = "arrivalDate"),
            @Mapping(source = "arrivalTime", target = "arrivalTime"),
            @Mapping(source = "numberOfPassenger", target = "numberOfPassenger"),
            @Mapping(source = "vehicleType", target = "vehicleType"),
            @Mapping(source = "driver.matricule", target = "matricule")
    })
    Trip tripRequestDTOToTrip(TripRequestDTO tripRequestDTO);

    TripRequestDTO tripToTripRequestDTO(Trip trip);

}

*/
