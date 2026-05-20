package com.example.weapon_microservice.model.textile.dto;

import com.example.weapon_microservice.model.textile.enums.TextileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TextileRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private TextileType type;
}
