package com.example.weapon_microservice.model.bayonet.dto;

import com.example.weapon_microservice.model.bayonet.enums.BayonetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BayonetRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @Positive
    private double bladeLength;
    @NotNull
    private BayonetType type;
    @NotNull
    private List<String> compatibleWeaponsIds;
}
