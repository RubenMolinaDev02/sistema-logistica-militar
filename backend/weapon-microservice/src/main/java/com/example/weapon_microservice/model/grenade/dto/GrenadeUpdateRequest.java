package com.example.weapon_microservice.model.grenade.dto;

import com.example.weapon_microservice.model.grenade.enums.ArmingMethod;
import com.example.weapon_microservice.model.grenade.enums.GrenadeType;
import lombok.Data;

@Data
public class GrenadeUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private Double fuzeDelay;
    private Double armingDistance;
    private GrenadeType type;
    private ArmingMethod armingMethod;
    private Boolean lethal;
}
