package com.example.weapon_microservice.model.manufacturer.dto;

import com.example.weapon_microservice.model.manufacturer.WeaponManufacturerModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class WeaponManufacturerResponse {
    private String id;
    private String reference;
    private String name;
    private String country;
    private String logo;
}
