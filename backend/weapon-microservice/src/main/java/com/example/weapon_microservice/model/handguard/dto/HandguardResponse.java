package com.example.weapon_microservice.model.handguard.dto;

import com.example.weapon_microservice.model.common.enums.MountType;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.handguard.enums.HandguardMaterial;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class HandguardResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private MountType mountType;
    private HandguardMaterial material;
    private List<PlatformResponse> compatiblePlatforms;
}
