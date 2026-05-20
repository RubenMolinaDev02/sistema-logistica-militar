package com.example.weapon_microservice.model.handguard.mapper;

import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.handguard.dto.HandguardRequest;
import com.example.weapon_microservice.model.handguard.dto.HandguardResponse;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;

import java.util.List;
import java.util.stream.Collectors;

public class HandguardMapper {
    public static HandguardModel modelFromRequest(HandguardRequest handguard, String id, String reference) {
        return HandguardModel.builder()
                .id(id)
                .reference(reference)
                .name(handguard.getName())
                .image(handguard.getImage())
                .mountType(handguard.getMountType())
                .material(handguard.getMaterial())
                .compatiblePlatformsIds(handguard.getCompatiblePlatformsIds())
                .build();
    }
    public static HandguardResponse responseFromModel(HandguardModel handguard, List<PlatformResponse> compatiblePlatforms) {
        return HandguardResponse.builder()
                .id(handguard.getId())
                .reference(handguard.getReference())
                .name(handguard.getName())
                .image(handguard.getImage())
                .mountType(handguard.getMountType())
                .material(handguard.getMaterial())
                .compatiblePlatforms(compatiblePlatforms)
                .build();
    }

    public static HandguardResponse responseFromModelSimple(HandguardModel handguard){
        return HandguardResponse.builder()
                .id(handguard.getId())
                .reference(handguard.getReference())
                .name(handguard.getName())
                .image(handguard.getImage())
                .mountType(handguard.getMountType())
                .material(handguard.getMaterial())
                .build();
    }

    public static List<HandguardResponse> resposeFromModelList(List<HandguardModel> allHandguards) {
        return allHandguards.stream()
                .map(HandguardMapper::responseFromModelSimple)
                .collect(Collectors.toList());
    }
}
