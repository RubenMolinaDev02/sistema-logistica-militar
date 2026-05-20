package com.example.weapon_microservice.model.miscItems.mapper;

import com.example.weapon_microservice.model.miscItems.MiscItemModel;
import com.example.weapon_microservice.model.miscItems.dto.MiscItemRequest;
import com.example.weapon_microservice.model.miscItems.dto.MiscItemResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MiscItemMapper {
    public static MiscItemModel modelFromRequest(MiscItemRequest request, String id, String reference){
        return MiscItemModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .type(request.getType())
                .image(request.getImage())
                .build();
    }

    public static MiscItemResponse responseFromModel(MiscItemModel model){
        return MiscItemResponse
                .builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .type(model.getType())
                .image(model.getImage())
                .build();
    }

    public static List<MiscItemResponse> responseFromModelList(List<MiscItemModel> models){
        return models.stream()
                .map(MiscItemMapper::responseFromModel)
                .collect(Collectors.toList());
    }
}
