package com.example.weapon_microservice.model.magazine.dto;

import com.example.weapon_microservice.model.magazine.enums.MagazineType;
import lombok.Data;

import java.util.List;
@Data
public class MagazineUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private List<String> compatiblePlatformsIds;
    private String caliberId;
    private int capacity;
    private MagazineType type;
}
