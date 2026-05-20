package com.example.weapon_microservice.model.armorVest.dto;

import com.example.weapon_microservice.model.armorVest.enums.SoftArmorLevel;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ArmorVestResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private SoftArmorLevel softArmor;
    private boolean compatibleWithPlates;
    private ServiceStatus status;
}
