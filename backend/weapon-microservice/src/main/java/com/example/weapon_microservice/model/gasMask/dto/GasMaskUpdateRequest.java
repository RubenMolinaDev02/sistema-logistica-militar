package com.example.weapon_microservice.model.gasMask.dto;

import com.example.weapon_microservice.model.common.enums.GasMaskStandar;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import lombok.Data;

@Data
public class GasMaskUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private GasMaskStandar standard;
    private ServiceStatus status;
}
