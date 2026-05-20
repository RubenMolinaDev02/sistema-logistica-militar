package com.example.weapon_microservice.model.helmet.dto;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.helmet.enums.HelmetLevel;
import com.example.weapon_microservice.model.helmet.enums.HelmetMaterial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class HelmetRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private HelmetLevel level;
    @NotNull
    private HelmetMaterial material;
    @Positive
    private double weight;
    @NotNull
    private ServiceStatus status;
}
