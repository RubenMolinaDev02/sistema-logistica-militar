package com.example.weapon_microservice.model.ammo.dto;

import com.example.weapon_microservice.model.ammo.enums.AmmoType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AmmoRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String caliberId;
    @NotNull
    private AmmoType type;
}
