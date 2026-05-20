package com.example.unit_microservice.model.unit.mapper;

import com.example.unit_microservice.model.unit.UnitModel;
import com.example.unit_microservice.model.unit.dto.UnitRequest;
import com.example.unit_microservice.model.unit.dto.UnitResponse;

import java.util.List;

public class UnitMapper {
    public static UnitModel modelFromRequest(UnitRequest request, String id, String ref){
        return UnitModel.builder()
                .id(id)
                .reference(ref)
                .name(request.getName())
                .type(request.getType())
                .parentUnitId(request.getParentUnitId())
                .build();
    }

    public static UnitResponse responseFromModel(UnitModel model){
        return UnitResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .type(model.getType())
                .parentUnitId(model.getParentUnitId())
                .build();
    }

    public static List<UnitResponse> responseFromModelList(List<UnitModel> models){
        return models.stream()
                .map(UnitMapper::responseFromModel)
                .toList();
    }
}
