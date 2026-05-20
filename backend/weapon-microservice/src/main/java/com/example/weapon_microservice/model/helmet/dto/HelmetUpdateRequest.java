package com.example.weapon_microservice.model.helmet.dto;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.helmet.enums.HelmetLevel;
import com.example.weapon_microservice.model.helmet.enums.HelmetMaterial;
import lombok.Data;

@Data
public class HelmetUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private HelmetLevel level;
    private HelmetMaterial material;
    private Double weight;
    private ServiceStatus status;
}
