package com.example.weapon_microservice.model.gasMask.dto;

import com.example.weapon_microservice.model.common.enums.GasMaskStandar;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GasMaskRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private GasMaskStandar standard;
    @NotNull
    private ServiceStatus status;
}
