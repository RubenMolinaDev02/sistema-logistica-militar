package com.example.weapon_microservice.model.optic.dto;

import com.example.weapon_microservice.model.common.enums.SightMountType;
import com.example.weapon_microservice.model.optic.OpticModel;
import com.example.weapon_microservice.model.optic.enums.OpticType;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OpticRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private SightMountType mountType;
    @NotNull
    private OpticType type;
    @PositiveOrZero
    private int minZoom;
    @PositiveOrZero
    private int maxZoom;
}
