package com.example.weapon_microservice.model.manufacturer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ManufacturerRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String country;
    @NotNull
    private String logo;
}
