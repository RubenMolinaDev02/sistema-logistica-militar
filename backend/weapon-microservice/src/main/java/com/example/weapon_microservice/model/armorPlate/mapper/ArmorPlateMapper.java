package com.example.weapon_microservice.model.armorPlate.mapper;

import com.example.weapon_microservice.model.armorPlate.ArmorPlateModel;
import com.example.weapon_microservice.model.armorPlate.dto.ArmorPlateRequest;
import com.example.weapon_microservice.model.armorPlate.dto.ArmorPlateResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ArmorPlateMapper {
    public static ArmorPlateModel modelFromRequest(ArmorPlateRequest request, String id, String reference){
        return ArmorPlateModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .image(request.getImage())
                .level(request.getLevel())
                .material(request.getMaterial())
                .weight(request.getWeight())
                .status(request.getStatus())
                .build();
    }

    public static ArmorPlateResponse responseFromModel(ArmorPlateModel model){
        return ArmorPlateResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .image(model.getImage())
                .level(model.getLevel())
                .material(model.getMaterial())
                .weight(model.getWeight())
                .status(model.getStatus())
                .build();
    }

    public static List<ArmorPlateResponse> responseFromModelList(List<ArmorPlateModel> models){
        return models.stream()
                .map(ArmorPlateMapper::responseFromModel)
                .collect(Collectors.toList());
    }
}
