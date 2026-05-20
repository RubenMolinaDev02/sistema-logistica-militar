package com.example.weapon_microservice.model.armorPlate.dto;

import com.example.weapon_microservice.model.armorPlate.enums.PlateLevel;
import com.example.weapon_microservice.model.armorPlate.enums.PlateMaterial;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ArmorPlateRequest {
    @NotBlank
    private String name;
    @NotNull
    private PlateLevel level;
    @NotNull
    private String image;
    @NotNull
    private PlateMaterial material;
    @Positive
    private double weight;
    @NotNull
    private ServiceStatus status;
}
