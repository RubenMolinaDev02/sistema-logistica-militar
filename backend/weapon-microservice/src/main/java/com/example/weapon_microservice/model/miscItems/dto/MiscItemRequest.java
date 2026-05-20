package com.example.weapon_microservice.model.miscItems.dto;

import com.example.weapon_microservice.model.miscItems.enums.MiscType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MiscItemRequest {
    @NotBlank
    private String name;
    @NotNull
    private MiscType type;
    @NotNull
    private String image;
}
