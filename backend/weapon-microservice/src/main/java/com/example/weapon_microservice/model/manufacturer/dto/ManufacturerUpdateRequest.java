package com.example.weapon_microservice.model.manufacturer.dto;

import lombok.Data;

@Data
public class ManufacturerUpdateRequest {
    private String reference;
    private String name;
    private String country;
    private String logo;
}
