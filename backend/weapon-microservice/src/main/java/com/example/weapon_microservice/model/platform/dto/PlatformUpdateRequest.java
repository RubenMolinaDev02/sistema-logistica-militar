package com.example.weapon_microservice.model.platform.dto;

import lombok.Data;

@Data
public class PlatformUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private String description;
}
