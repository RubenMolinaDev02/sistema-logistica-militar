package com.example.location_microservice.model.location;

import com.example.location_microservice.model.location.enums.LocationStatus;
import com.example.location_microservice.model.location.enums.LocationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "location")
@Builder
@Data
public class LocationModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String country;
    private LocationType type;
    private int maxTroops;
    private LocationStatus status;
}