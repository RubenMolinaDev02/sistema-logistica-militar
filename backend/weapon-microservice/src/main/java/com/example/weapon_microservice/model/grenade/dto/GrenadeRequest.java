package com.example.weapon_microservice.model.grenade.dto;

import com.example.weapon_microservice.model.grenade.enums.ArmingMethod;
import com.example.weapon_microservice.model.grenade.enums.GrenadeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class GrenadeRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @PositiveOrZero
    private double fuzeDelay;
    @PositiveOrZero
    private double armingDistance;
    @NotNull
    private GrenadeType type;
    @NotNull
    private ArmingMethod armingMethod;
    private boolean lethal;
}
