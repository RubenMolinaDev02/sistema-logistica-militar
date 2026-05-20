package com.example.weapon_microservice.model.nvg.mapper;

import com.example.weapon_microservice.model.nvg.NvgModel;
import com.example.weapon_microservice.model.nvg.dto.NvgRequest;
import com.example.weapon_microservice.model.nvg.dto.NvgResponse;

import java.util.List;

public class NvgMapper {
    public static NvgModel modelFromRequest(NvgRequest request, String id, String reference){
        return NvgModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .image(request.getImage())
                .generation(request.getGeneration())
                .range(request.getRange())
                .status(request.getStatus())
                .build();
    }

    public static NvgResponse responseFromModel(NvgModel model){
        return NvgResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .image(model.getImage())
                .generation(model.getGeneration())
                .range(model.getRange())
                .status(model.getStatus())
                .build();
    }

    public static List<NvgResponse> responseFromModelList(List<NvgModel> models){
        return models.stream()
                .map(NvgMapper::responseFromModel)
                .toList();
    }
}
