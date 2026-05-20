package com.example.weapon_microservice.model.ammo.mapper;

import com.example.weapon_microservice.model.ammo.AmmoModel;
import com.example.weapon_microservice.model.ammo.dto.AmmoRequest;
import com.example.weapon_microservice.model.ammo.dto.AmmoResponse;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;

import java.util.List;
import java.util.stream.Collectors;

public class AmmoMapper {
    public static AmmoModel modelFromRequest(AmmoRequest request, String id, String reference){
        return AmmoModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .caliberId(request.getCaliberId())
                .type(request.getType())
                .build();
    }

    public static AmmoResponse responseFromModel(AmmoModel model, CaliberResponse caliberResponse){
        return AmmoResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .caliber(caliberResponse)
                .type(model.getType())
                .build();
    }

    public static AmmoResponse responseFromModelSimple(AmmoModel model){
        return AmmoResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .type(model.getType())
                .build();
    }

    public static List<AmmoResponse> responseFromModelListSimple(List<AmmoModel> models){
        return models.stream()
                .map(AmmoMapper::responseFromModelSimple)
                .collect(Collectors.toList());
    }
}
