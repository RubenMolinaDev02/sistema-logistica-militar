package com.example.unit_microservice.model.unit.dto;

import com.example.unit_microservice.model.unit.enums.UnitType;
import lombok.Data;

@Data
public class UnitUpdateRequest {
    private String reference;
    private String name;
    private UnitType type;
    private String parentUnitId;
}
