package com.example.weapon_microservice.model.textile.dto;

import com.example.weapon_microservice.model.textile.enums.TextileType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TextileResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private TextileType type;
}
