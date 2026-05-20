package com.example.weapon_microservice.model.nvg.dto;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.nvg.enums.NvgGen;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class NvgRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private NvgGen generation;
    @Positive
    private int range;
    @NotNull
    private ServiceStatus status;
}
