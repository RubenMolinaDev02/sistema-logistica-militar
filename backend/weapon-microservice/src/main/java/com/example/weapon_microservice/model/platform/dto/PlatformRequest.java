package com.example.weapon_microservice.model.platform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
public class PlatformRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotBlank
    private String description;
}
