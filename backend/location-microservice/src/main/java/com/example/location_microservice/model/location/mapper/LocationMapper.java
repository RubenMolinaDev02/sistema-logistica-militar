package com.example.location_microservice.model.location.mapper;

import com.example.location_microservice.model.location.LocationModel;
import com.example.location_microservice.model.location.dto.LocationRequest;
import com.example.location_microservice.model.location.dto.LocationResponse;

import java.util.List;

public class LocationMapper {
    public static LocationModel modelFromRequest(LocationRequest request, String id, String reference){
        return LocationModel.builder()
                .id(id)
                .reference(reference)
                .maxTroops(request.getMaxTroops())
                .name(request.getName())
                .type(request.getType())
                .status(request.getStatus())
                .build();
    }

    public static LocationResponse responseFromModel(LocationModel model){
        return LocationResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .name(model.getName())
                .maxTroops(model.getMaxTroops())
                .type(model.getType())
                .status(model.getStatus())
                .build();
    }

    public static List<LocationResponse> responseFromModelList(List<LocationModel> models){
        return models.stream()
                .map(LocationMapper::responseFromModel)
                .toList();
    }
}
