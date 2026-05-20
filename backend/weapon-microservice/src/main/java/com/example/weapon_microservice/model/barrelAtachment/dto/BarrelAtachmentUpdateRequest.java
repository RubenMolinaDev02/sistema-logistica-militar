package com.example.weapon_microservice.model.barrelAtachment.dto;

import com.example.weapon_microservice.model.barrelAtachment.enums.BarrelAttachmentType;
import lombok.Data;

import java.util.List;

@Data
public class BarrelAtachmentUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private BarrelAttachmentType type;
    private List<String> compatiblePlatformsIds;
    private String compatibleCaliber;
}
