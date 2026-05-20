package com.example.weapon_microservice.model.textile.dto;

import com.example.weapon_microservice.model.textile.enums.TextileType;
import lombok.Data;

@Data
public class TextileUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private TextileType type;
}
