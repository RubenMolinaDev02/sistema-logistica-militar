package com.example.weapon_microservice.model.bayonet.dto;

import com.example.weapon_microservice.model.bayonet.enums.BayonetType;
import lombok.Data;

import java.util.List;
@Data
public class BayonetUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private Double bladeLength;
    private BayonetType type;
    private List<String> compatibleWeaponsIds;
}
