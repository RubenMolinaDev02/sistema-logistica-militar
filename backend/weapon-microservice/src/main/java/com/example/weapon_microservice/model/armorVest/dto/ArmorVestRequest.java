package com.example.weapon_microservice.model.armorVest.dto;

import com.example.weapon_microservice.model.armorVest.enums.SoftArmorLevel;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ArmorVestRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private SoftArmorLevel softArmor;
    private boolean compatibleWithPlates;
    @NotNull
    private ServiceStatus status;
}
