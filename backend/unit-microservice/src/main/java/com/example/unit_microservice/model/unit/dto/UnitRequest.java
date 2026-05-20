package com.example.unit_microservice.model.unit.dto;

import com.example.unit_microservice.model.unit.enums.UnitType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UnitRequest {
    @NotBlank
    private String name;
    @NotNull
    private UnitType type;
    private String parentUnitId;
}
