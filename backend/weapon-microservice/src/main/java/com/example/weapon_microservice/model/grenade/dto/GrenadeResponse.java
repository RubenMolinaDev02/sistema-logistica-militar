package com.example.weapon_microservice.model.grenade.dto;

import com.example.weapon_microservice.model.grenade.enums.ArmingMethod;
import com.example.weapon_microservice.model.grenade.enums.GrenadeType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class GrenadeResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private double fuzeDelay;
    private double armingDistance;
    private GrenadeType type;
    private ArmingMethod armingMethod;
    private boolean lethal;
}
