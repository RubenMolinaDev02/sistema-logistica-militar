package com.example.weapon_microservice.model.holster.dto;

import com.example.weapon_microservice.model.holster.enums.HolsterType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class HolsterRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private HolsterType type;
    @NotNull
    private List<String> compatibleWeaponIds;
    private boolean universal;
}
