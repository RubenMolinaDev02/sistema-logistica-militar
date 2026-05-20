package com.example.weapon_microservice.model.armorVest.dto;

import com.example.weapon_microservice.model.armorVest.enums.SoftArmorLevel;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import lombok.Data;

@Data
public class ArmorVestUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private SoftArmorLevel softArmor;
    private Boolean compatibleWithPlates;
    private ServiceStatus status;
}
