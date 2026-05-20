package com.example.weapon_microservice.model.handguard.dto;

import com.example.weapon_microservice.model.common.enums.MountType;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.handguard.enums.HandguardMaterial;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class HandguardRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private MountType mountType;
    @NotNull
    private HandguardMaterial material;
    @NotNull
    private List<String> compatiblePlatformsIds;
}
