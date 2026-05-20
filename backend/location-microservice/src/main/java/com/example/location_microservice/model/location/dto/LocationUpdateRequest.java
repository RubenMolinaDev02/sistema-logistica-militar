package com.example.location_microservice.model.location.dto;

import com.example.location_microservice.model.location.enums.LocationStatus;
import com.example.location_microservice.model.location.enums.LocationType;
import lombok.Data;

@Data
public class LocationUpdateRequest {
    private String reference;
    private String name;
    private String country;
    private LocationType type;
    private int maxTroops;
    private LocationStatus status;
}
