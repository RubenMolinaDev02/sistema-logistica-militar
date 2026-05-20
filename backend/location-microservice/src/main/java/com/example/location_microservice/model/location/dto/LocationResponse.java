package com.example.location_microservice.model.location.dto;

import com.example.location_microservice.model.location.enums.LocationStatus;
import com.example.location_microservice.model.location.enums.LocationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class LocationResponse {
    private String id;
    private String reference;
    private String name;
    private String country;
    private LocationType type;
    private int maxTroops;
    private LocationStatus status;
}
