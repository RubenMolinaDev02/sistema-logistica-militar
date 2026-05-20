package com.example.weapon_microservice.model.armorVest.mapper;

import com.example.weapon_microservice.model.armorVest.ArmorVestModel;
import com.example.weapon_microservice.model.armorVest.dto.ArmorVestRequest;
import com.example.weapon_microservice.model.armorVest.dto.ArmorVestResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ArmorVestMapper {
    public static ArmorVestModel modelFromRequest(ArmorVestRequest request, String id, String reference){
        return ArmorVestModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .image(request.getImage())
                .softArmor(request.getSoftArmor())
                .compatibleWithPlates(request.isCompatibleWithPlates())
                .status(request.getStatus())
                .build();
    }

    public static ArmorVestResponse responseFromModel(ArmorVestModel model){
        return ArmorVestResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .image(model.getImage())
                .softArmor(model.getSoftArmor())
                .compatibleWithPlates(model.isCompatibleWithPlates())
                .status(model.getStatus())
                .build();
    }

    public static List<ArmorVestResponse> responseFromModelList(List<ArmorVestModel> models){
        return models.stream()
                .map(ArmorVestMapper::responseFromModel)
                .collect(Collectors.toList());
    }
}
