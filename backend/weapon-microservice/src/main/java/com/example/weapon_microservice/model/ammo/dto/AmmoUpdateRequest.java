package com.example.weapon_microservice.model.ammo.dto;

import com.example.weapon_microservice.model.ammo.enums.AmmoType;
import lombok.Data;

@Data
public class AmmoUpdateRequest {
    private String reference;
    private String name;
    private String caliberId;
    private AmmoType type;
}
