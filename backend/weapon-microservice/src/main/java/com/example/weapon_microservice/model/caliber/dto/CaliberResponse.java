package com.example.weapon_microservice.model.caliber.dto;

import com.example.weapon_microservice.model.ammo.dto.AmmoResponse;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.enums.CaliberStandard;
import com.example.weapon_microservice.model.caliber.enums.CaliberType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class CaliberResponse {
    private String id;
    private String reference;
    private String name;
    private CaliberType type;
    private CaliberStandard standard;
    private List<AmmoResponse> ammo;
}
