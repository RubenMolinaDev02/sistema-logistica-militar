package com.example.unit_microservice.model.unit.dto;

import com.example.unit_microservice.model.unit.enums.UnitType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class UnitResponse {
    private String id;
    private String reference;
    private String name;
    private UnitType type;
    private String parentUnitId;
}
