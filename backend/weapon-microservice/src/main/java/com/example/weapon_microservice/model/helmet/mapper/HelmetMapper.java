package com.example.weapon_microservice.model.helmet.mapper;

import com.example.weapon_microservice.model.helmet.HelmetModel;
import com.example.weapon_microservice.model.helmet.dto.HelmetRequest;
import com.example.weapon_microservice.model.helmet.dto.HelmetResponse;

import java.util.List;
import java.util.stream.Collectors;

public class HelmetMapper {
    public static HelmetModel modelFromRequest(HelmetRequest request, String id, String reference){
        return HelmetModel.builder()
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

    public static HelmetResponse responseFromModel(HelmetModel model){
        return HelmetResponse.builder()
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

    public static List<HelmetResponse> responseFromModelList(List<HelmetModel> models){
        return models.stream()
                .map(HelmetMapper::responseFromModel)
                .collect(Collectors.toList());
    }
}
