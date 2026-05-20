package com.example.weapon_microservice.model.optic.dto;

import com.example.weapon_microservice.model.common.enums.SightMountType;
import com.example.weapon_microservice.model.optic.enums.OpticType;
import lombok.Data;

@Data
public class OpticUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private SightMountType mountType;
    private OpticType type;
    private Integer minZoom;
    private Integer maxZoom;
}
