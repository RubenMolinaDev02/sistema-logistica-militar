package com.example.weapon_microservice.model.caliber.dto;

import com.example.weapon_microservice.model.caliber.enums.CaliberStandard;
import com.example.weapon_microservice.model.caliber.enums.CaliberType;
import lombok.Data;

@Data
public class CaliberUpdateRequest {
    private String reference;
    private String name;
    private CaliberType type;
    private CaliberStandard standard;
}
