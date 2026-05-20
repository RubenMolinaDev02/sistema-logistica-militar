package com.example.weapon_microservice.model.armorPlate.dto;

import com.example.weapon_microservice.model.armorPlate.enums.PlateLevel;
import com.example.weapon_microservice.model.armorPlate.enums.PlateMaterial;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import lombok.Data;

@Data
public class ArmorPlateUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private PlateLevel level;
    private PlateMaterial material;
    private Double weight;
    private ServiceStatus status;
}
