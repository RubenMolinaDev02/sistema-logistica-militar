package com.example.weapon_microservice.model.textile.mapper;

import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.model.textile.dto.TextileRequest;
import com.example.weapon_microservice.model.textile.dto.TextileResponse;

import java.util.List;

public class TextileMapper {
    public static TextileModel modelFromRequest(TextileRequest request, String id, String reference){
        return TextileModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .image(request.getImage())
                .type(request.getType())
                .build();
    }

    public static TextileResponse responseFromModel(TextileModel model){
        return TextileResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .image(model.getImage())
                .type(model.getType())
                .build();
    }

    public static List<TextileResponse> responseFromModelList(List<TextileModel> models){
        return models.stream()
                .map(TextileMapper::responseFromModel)
                .toList();
    }
}
