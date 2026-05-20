package com.example.weapon_microservice.model.gasMaskFilter.dto;

import com.example.weapon_microservice.model.common.enums.GasMaskStandar;
import lombok.Data;

@Data
public class GasMaskFilterUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private Integer useHours;
    private GasMaskStandar standard;
}
