package com.example.weapon_microservice.model.grenade.mapper;

import com.example.weapon_microservice.model.grenade.GrenadeModel;
import com.example.weapon_microservice.model.grenade.dto.GrenadeRequest;
import com.example.weapon_microservice.model.grenade.dto.GrenadeResponse;

import java.util.List;

public class GrenadeMapper {
    public static GrenadeModel modelFromRequest(GrenadeRequest grenadeRequest, String id, String reference){
        return GrenadeModel.builder()
                .id(id)
                .reference(reference)
                .name(grenadeRequest.getName())
                .image(grenadeRequest.getImage())
                .fuzeDelay(grenadeRequest.getFuzeDelay())
                .armingDistance(grenadeRequest.getArmingDistance())
                .type(grenadeRequest.getType())
                .armingMethod(grenadeRequest.getArmingMethod())
                .lethal(grenadeRequest.isLethal())
                .build();
    }

    public static GrenadeResponse responseFromModel(GrenadeModel model){
        return GrenadeResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .image(model.getImage())
                .fuzeDelay(model.getFuzeDelay())
                .armingDistance(model.getArmingDistance())
                .type(model.getType())
                .armingMethod(model.getArmingMethod())
                .lethal(model.isLethal())
                .build();
    }

    public static List<GrenadeResponse> responseFromModelList(List<GrenadeModel> models){
        return models.stream()
                .map(GrenadeMapper::responseFromModel)
                .toList();
    }
}
