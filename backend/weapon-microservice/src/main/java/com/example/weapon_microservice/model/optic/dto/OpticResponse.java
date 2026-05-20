package com.example.weapon_microservice.model.optic.dto;

import com.example.weapon_microservice.model.common.enums.SightMountType;
import com.example.weapon_microservice.model.optic.enums.OpticType;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Builder
public class OpticResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private SightMountType mountType;
    private OpticType type;
    private int minZoom;
    private int maxZoom;
    private boolean variable;
}
