package com.example.weapon_microservice.model.ammo.dto;

import com.example.weapon_microservice.model.ammo.enums.AmmoType;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class AmmoResponse {
    private String id;
    private String reference;
    private String name;
    private CaliberResponse caliber;
    private AmmoType type;
}
