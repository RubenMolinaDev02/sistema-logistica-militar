package com.example.weapon_microservice.model.magazine.mapper;

import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.caliber.mapper.CaliberMapper;
import com.example.weapon_microservice.model.magazine.MagazineModel;
import com.example.weapon_microservice.model.magazine.dto.MagazineRequest;
import com.example.weapon_microservice.model.magazine.dto.MagazineResponse;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.platform.mapper.PlatformMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MagazineMapper {
    public static MagazineModel modelFromRequest(MagazineRequest magazineRequest, String id, String reference){
        return MagazineModel.builder()
                .id(id)
                .reference(reference)
                .name(magazineRequest.getName())
                .image(magazineRequest.getImage())
                .compatiblePlatformsIds(magazineRequest.getCompatiblePlatformsIds())
                .caliberId(magazineRequest.getCaliberId())
                .capacity(magazineRequest.getCapacity())
                .type(magazineRequest.getType())
                .build();
    }
    public static MagazineResponse responseFromModel(MagazineModel magazineModel, List<PlatformModel> platformModels, CaliberModel caliber){
        List<PlatformResponse> platformResponses = PlatformMapper.responseFromModelList(platformModels);
        CaliberResponse caliberResponse = CaliberMapper.responseFromModelSimple(caliber);

        return MagazineResponse.builder()
                .id(magazineModel.getId())
                .reference(magazineModel.getReference())
                .name(magazineModel.getName())
                .image(magazineModel.getImage())
                .compatiblePlatforms(platformResponses)
                .caliber(caliberResponse)
                .capacity(magazineModel.getCapacity())
                .type(magazineModel.getType())
                .build();
    }

    public static MagazineResponse responseFromModelSimple(MagazineModel magazineModel){

        return MagazineResponse.builder()
                .id(magazineModel.getId())
                .reference(magazineModel.getReference())
                .name(magazineModel.getName())
                .image(magazineModel.getImage())
                .capacity(magazineModel.getCapacity())
                .type(magazineModel.getType())
                .build();
    }

    public static List<MagazineResponse> responseFromModelListSimple(List<MagazineModel> allMagazines) {
        return allMagazines.stream()
                .map(MagazineMapper::responseFromModelSimple)
                .collect(Collectors.toList());
    }
}
