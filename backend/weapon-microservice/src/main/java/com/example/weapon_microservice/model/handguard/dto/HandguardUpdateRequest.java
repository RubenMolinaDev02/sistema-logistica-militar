package com.example.weapon_microservice.model.handguard.dto;

import com.example.weapon_microservice.model.common.enums.MountType;
import com.example.weapon_microservice.model.handguard.enums.HandguardMaterial;
import lombok.Data;

import java.util.List;

@Data
public class HandguardUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private MountType mountType;
    private HandguardMaterial material;
    private List<String> compatiblePlatformsIds;
}
