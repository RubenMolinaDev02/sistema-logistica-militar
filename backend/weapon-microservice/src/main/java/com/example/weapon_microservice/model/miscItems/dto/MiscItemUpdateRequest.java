package com.example.weapon_microservice.model.miscItems.dto;

import com.example.weapon_microservice.model.miscItems.enums.MiscType;
import lombok.Data;

@Data
public class MiscItemUpdateRequest {
    private String reference;
    private String name;
    private MiscType type;
    private String image;
}
