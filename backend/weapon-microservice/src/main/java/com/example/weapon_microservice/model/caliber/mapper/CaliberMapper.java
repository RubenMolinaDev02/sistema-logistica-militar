package com.example.weapon_microservice.model.caliber.mapper;

import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.dto.CaliberRequest;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CaliberMapper {

    public static CaliberModel modelFromRequest(CaliberRequest request, String id, String reference){
        return CaliberModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .type(request.getType())
                .standard(request.getStandard())
                .build();
    }

    public static CaliberResponse responseFromModel(CaliberModel caliber) {
        return CaliberResponse.builder()
                .id(caliber.getId())
                .reference(caliber.getReference())
                .name(caliber.getName())
                .type(String.valueOf(caliber.getType()))
                .standard(String.valueOf(caliber.getStandard()))
                .build();
    }

    public static List<CaliberResponse> responseFromModelList(List<CaliberModel> allCalibers) {
        return allCalibers.stream()
                .map(CaliberMapper::responseFromModel)
                .collect(Collectors.toList());
    }
}
