package com.example.weapon_microservice.model.magazine.dto;

import com.example.weapon_microservice.model.magazine.MagazineModel;
import com.example.weapon_microservice.model.magazine.enums.MagazineType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MagazineRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private List<String> compatiblePlatformsIds;
    @NotBlank
    private String caliberId;
    @Positive
    private int capacity;
    @NotNull
    private MagazineType type;
}
