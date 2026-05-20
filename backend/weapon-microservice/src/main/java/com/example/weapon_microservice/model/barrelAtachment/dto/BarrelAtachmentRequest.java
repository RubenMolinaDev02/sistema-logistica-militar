package com.example.weapon_microservice.model.barrelAtachment.dto;

import com.example.weapon_microservice.model.barrelAtachment.enums.BarrelAttachmentType;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BarrelAtachmentRequest {
    @NotBlank
    private String name;
    @NotNull
    private BarrelAttachmentType type;
    @NotNull
    private List<String> compatiblePlatforms;
    @NotBlank
    private String compatibleCaliber;
    @NotNull
    private String image;
}
