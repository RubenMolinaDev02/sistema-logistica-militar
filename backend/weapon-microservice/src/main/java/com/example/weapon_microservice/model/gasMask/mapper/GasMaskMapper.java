package com.example.weapon_microservice.model.gasMask.mapper;

import com.example.weapon_microservice.model.gasMask.GasMaskModel;
import com.example.weapon_microservice.model.gasMask.dto.GasMaskRequest;
import com.example.weapon_microservice.model.gasMask.dto.GasMaskResponse;

import java.util.List;

public class GasMaskMapper {
    public static GasMaskModel modelFromRequest(GasMaskRequest gasMaskRequest, String id, String reference){
        return GasMaskModel.builder()
                .id(id)
                .reference(reference)
                .name(gasMaskRequest.getName())
                .image(gasMaskRequest.getImage())
                .standard(gasMaskRequest.getStandard())
                .status(gasMaskRequest.getStatus())
                .build();
    }

    public static GasMaskResponse responseFromModel(GasMaskModel model){
        return GasMaskResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .image(model.getImage())
                .standard(model.getStandard())
                .status(model.getStatus())
                .build();
    }

    public static List<GasMaskResponse> responseFromModelList(List<GasMaskModel> models){
        return models.stream()
                .map(GasMaskMapper::responseFromModel)
                .toList();
    }
}
