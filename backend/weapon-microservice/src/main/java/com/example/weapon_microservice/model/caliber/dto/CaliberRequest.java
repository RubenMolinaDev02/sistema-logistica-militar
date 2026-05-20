package com.example.weapon_microservice.model.caliber.dto;

import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.enums.CaliberStandard;
import com.example.weapon_microservice.model.caliber.enums.CaliberType;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
@Getter
public class CaliberRequest {
    @NotBlank
    private String name;
    @NotNull
    private CaliberType type;
    @NotNull
    private CaliberStandard standard;
}
