package com.example.weapon_microservice.model.holster.mapper;

import com.example.weapon_microservice.model.holster.HolsterModel;
import com.example.weapon_microservice.model.holster.dto.HolsterRequest;
import com.example.weapon_microservice.model.holster.dto.HolsterResponse;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;

import java.util.List;
import java.util.stream.Collectors;

public class HolsterMapper {
    public static HolsterModel modelFromRequest(HolsterRequest request, String id, String reference){
        return HolsterModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .image(request.getImage())
                .type(request.getType())
                .compatibleWeaponIds(request.getCompatibleWeaponIds())
                .universal(request.isUniversal())
                .build();
    }

    public static HolsterResponse responseFromModel(HolsterModel model, List<WeaponResponse> responses){
        return HolsterResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .image(model.getImage())
                .type(model.getType())
                .compatibleWeapons(responses)
                .universal(model.isUniversal())
                .build();
    }

    public static HolsterResponse responseFromModelSimple(HolsterModel model){
        return HolsterResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .image(model.getImage())
                .type(model.getType())
                .universal(model.isUniversal())
                .build();
    }

    public static List<HolsterResponse> responseFromModelSimpleList(List<HolsterModel> models){
        return models.stream()
                .map(HolsterMapper::responseFromModelSimple)
                .collect(Collectors.toList());
    }
}
