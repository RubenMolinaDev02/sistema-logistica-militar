package com.example.location_microservice.model.location.dto;

import com.example.location_microservice.model.location.enums.LocationStatus;
import com.example.location_microservice.model.location.enums.LocationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class LocationRequest {
    @NotBlank
    private String name;
    @NotNull
    private LocationType type;
    @PositiveOrZero
    private int maxTroops;
    @NotBlank
    private String country;
    @NotNull
    private LocationStatus status;
}
