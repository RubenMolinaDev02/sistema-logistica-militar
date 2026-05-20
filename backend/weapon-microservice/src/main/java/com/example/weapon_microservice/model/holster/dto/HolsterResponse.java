package com.example.weapon_microservice.model.holster.dto;

import com.example.weapon_microservice.model.holster.enums.HolsterType;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class HolsterResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private HolsterType type;
    private List<WeaponResponse> compatibleWeapons;
    private boolean universal;
}
