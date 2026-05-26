package com.example.weapon_microservice.model.platform.mapper;

import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.model.platform.dto.PlatformRequest;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PlatformMapper {
    public static PlatformModel modelFromRequest(PlatformRequest request, String id, String reference){
        return PlatformModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .image(request.getImage())
                .description(request.getDescription())
                .build();
    }

    public static PlatformResponse responseFromModel(PlatformModel platform) {
        return PlatformResponse.builder()
                .id(platform.getId())
                .reference(platform.getReference())
                .name(platform.getName())
                .image(platform.getImage())
                .description(platform.getDescription())
                .build();
    }

    public static PlatformResponse responseFromModelDetail(PlatformModel platform, List<WeaponResponse> weapons) {
        return PlatformResponse.builder()
                .id(platform.getId())
                .reference(platform.getReference())
                .name(platform.getName())
                .image(platform.getImage())
                .description(platform.getDescription())
                .weapons(weapons)
                .build();
    }

    public static List<PlatformResponse> responseFromModelList(List<PlatformModel> platformModels){
        return platformModels.stream()
                .map(PlatformMapper::responseFromModel)
                .collect(Collectors.toList());
    }
}
