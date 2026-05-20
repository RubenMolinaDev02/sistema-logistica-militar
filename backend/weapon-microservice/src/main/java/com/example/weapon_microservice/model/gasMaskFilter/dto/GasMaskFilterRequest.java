package com.example.weapon_microservice.model.gasMaskFilter.dto;

import com.example.weapon_microservice.model.common.enums.GasMaskStandar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GasMaskFilterRequest {
    @NotBlank
    private String name;
    @Positive
    private int useHours;
    @NotNull
    private String image;
    @NotNull
    private GasMaskStandar standard;
}
