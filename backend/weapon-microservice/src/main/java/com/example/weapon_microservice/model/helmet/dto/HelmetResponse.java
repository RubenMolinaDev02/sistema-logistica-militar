package com.example.weapon_microservice.model.helmet.dto;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.helmet.enums.HelmetLevel;
import com.example.weapon_microservice.model.helmet.enums.HelmetMaterial;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class HelmetResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private HelmetLevel level;
    private HelmetMaterial material;
    private double weight;
    private ServiceStatus status;
}
