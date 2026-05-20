package com.example.weapon_microservice.model.gasMask.dto;

import com.example.weapon_microservice.model.common.enums.GasMaskStandar;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class GasMaskResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private GasMaskStandar standard;
    private ServiceStatus status;
}
