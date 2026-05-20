package com.example.weapon_microservice.model.optic.mapper;

import com.example.weapon_microservice.model.optic.OpticModel;
import com.example.weapon_microservice.model.optic.dto.OpticRequest;
import com.example.weapon_microservice.model.optic.dto.OpticResponse;

import java.util.List;
import java.util.stream.Collectors;

public class OpticMapper {
    public static OpticResponse responseFromModel(OpticModel optic) {
        return OpticResponse.builder()
                .id(optic.getId())
                .reference(optic.getReference())
                .name(optic.getName())
                .image(optic.getImage())
                .mountType(optic.getMountType())
                .type(optic.getType())
                .minZoom(optic.getMinZoom())
                .maxZoom(optic.getMaxZoom())
                .variable(optic.getMinZoom() != optic.getMaxZoom())
                .build();
    }

    public static List<OpticResponse> responseFromModelList(List<OpticModel> opticModels){
        return opticModels.stream()
                .map(OpticMapper::responseFromModel)
                .collect(Collectors.toList());
    }

    public static OpticModel modelFromRequest(OpticRequest model, String id, String reference) {
        return OpticModel.builder()
                .id(id)
                .reference(reference)
                .name(model.getName())
                .image(model.getImage())
                .mountType(model.getMountType())
                .type(model.getType())
                .minZoom(model.getMinZoom())
                .maxZoom(model.getMaxZoom())
                .build();
    }
}
