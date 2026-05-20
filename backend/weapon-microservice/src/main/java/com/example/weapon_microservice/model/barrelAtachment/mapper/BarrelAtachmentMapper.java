package com.example.weapon_microservice.model.barrelAtachment.mapper;

import com.example.weapon_microservice.model.barrelAtachment.BarrelAtachmentModel;
import com.example.weapon_microservice.model.barrelAtachment.dto.BarrelAtachmentRequest;
import com.example.weapon_microservice.model.barrelAtachment.dto.BarrelAtachmentResponse;
import com.example.weapon_microservice.model.barrelAtachment.enums.BarrelAttachmentType;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;

import java.util.List;
import java.util.stream.Collectors;

public class BarrelAtachmentMapper {
    public static BarrelAtachmentModel modelFromRequest(BarrelAtachmentRequest request, String id, String reference){
        return BarrelAtachmentModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .image(request.getImage())
                .type(request.getType())
                .compatiblePlatformsIds(request.getCompatiblePlatforms())
                .compatibleCaliber(request.getCompatibleCaliber())
                .build();
    }
    public static BarrelAtachmentResponse responseFromModel(BarrelAtachmentModel barrelAtachmentModel, CaliberResponse caliber, List<PlatformResponse> platforms){
        return BarrelAtachmentResponse.builder()
                .id(barrelAtachmentModel.getId())
                .reference(barrelAtachmentModel.getReference())
                .name(barrelAtachmentModel.getName())
                .image(barrelAtachmentModel.getImage())
                .type(barrelAtachmentModel.getType())
                .compatiblePlatforms(platforms)
                .compatibleCaliber(caliber)
                .supressor(barrelAtachmentModel.getType() == BarrelAttachmentType.SUPPRESSOR)
                .build();
    }
    public static BarrelAtachmentResponse responseFromModelSimple(BarrelAtachmentModel barrelAtachmentModel){
        return BarrelAtachmentResponse.builder()
                .id(barrelAtachmentModel.getId())
                .reference(barrelAtachmentModel.getReference())
                .name(barrelAtachmentModel.getName())
                .image(barrelAtachmentModel.getImage())
                .type(barrelAtachmentModel.getType())
                .supressor(barrelAtachmentModel.getType() == BarrelAttachmentType.SUPPRESSOR)
                .build();
    }
    public static List<BarrelAtachmentResponse> responseFromModelListSimple(List<BarrelAtachmentModel> barrelAtachmentModels){
        return barrelAtachmentModels.stream()
                .map(BarrelAtachmentMapper::responseFromModelSimple)
                .collect(Collectors.toList());
    }
}
