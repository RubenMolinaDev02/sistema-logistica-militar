package com.example.weapon_microservice.model.barrelAtachment.dto;

import com.example.weapon_microservice.model.barrelAtachment.enums.BarrelAttachmentType;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class BarrelAtachmentResponse {
    private String id;
    private String reference;
    private String name;
    private BarrelAttachmentType type;
    private List<PlatformResponse> compatiblePlatforms;
    private CaliberResponse compatibleCaliber;
    private boolean supressor;
    private String image;
}
