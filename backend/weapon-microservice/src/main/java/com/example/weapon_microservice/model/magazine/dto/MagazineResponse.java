package com.example.weapon_microservice.model.magazine.dto;

import com.example.weapon_microservice.model.magazine.MagazineModel;
import com.example.weapon_microservice.model.magazine.enums.MagazineType;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class MagazineResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private List<PlatformResponse> compatiblePlatforms;
    private CaliberResponse caliber;
    private int capacity;
    private MagazineType type;
}
