package com.example.weapon_microservice.model.bayonet.dto;

import com.example.weapon_microservice.model.bayonet.BayonetModel;
import com.example.weapon_microservice.model.bayonet.enums.BayonetType;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.mapper.WeaponMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class BayonetResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private double bladeLength;
    private BayonetType type;
    private List<WeaponResponse> compatibleWeapons;
}
