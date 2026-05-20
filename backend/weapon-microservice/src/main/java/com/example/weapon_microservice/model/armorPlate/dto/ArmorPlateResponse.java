package com.example.weapon_microservice.model.armorPlate.dto;

import com.example.weapon_microservice.model.armorPlate.enums.PlateLevel;
import com.example.weapon_microservice.model.armorPlate.enums.PlateMaterial;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ArmorPlateResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private PlateLevel level;
    private PlateMaterial material;
    private double weight;
    private ServiceStatus status;
}
