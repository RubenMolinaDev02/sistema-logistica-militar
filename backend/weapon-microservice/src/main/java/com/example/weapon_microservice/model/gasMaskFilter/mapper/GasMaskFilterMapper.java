package com.example.weapon_microservice.model.gasMaskFilter.mapper;

import com.example.weapon_microservice.model.gasMaskFilter.GasMaskFilterModel;
import com.example.weapon_microservice.model.gasMaskFilter.dto.GasMaskFilterRequest;
import com.example.weapon_microservice.model.gasMaskFilter.dto.GasMaskFilterResponse;

import java.util.List;

public class GasMaskFilterMapper {
    public static GasMaskFilterModel modelFromRequest(GasMaskFilterRequest request, String id, String reference){
        return GasMaskFilterModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .image(request.getImage())
                .useHours(request.getUseHours())
                .standard(request.getStandard())
                .build();
    }
    
    public static GasMaskFilterResponse responseFromModel(GasMaskFilterModel model){
        return GasMaskFilterResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .image(model.getImage())
                .useHours(model.getUseHours())
                .standard(model.getStandard())
                .build();
    }
    
    public static List<GasMaskFilterResponse> responseFromModelList(List<GasMaskFilterModel> models){
        return models.stream()
                .map(GasMaskFilterMapper::responseFromModel)
                .toList();
    }
}
