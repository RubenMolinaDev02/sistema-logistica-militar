package com.example.weapon_microservice.model.holster.dto;

import com.example.weapon_microservice.model.holster.enums.HolsterType;
import lombok.Data;

import java.util.List;
@Data
public class HolsterUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private HolsterType type;
    private List<String> compatibleWeaponIds;
    private Boolean universal;
}
