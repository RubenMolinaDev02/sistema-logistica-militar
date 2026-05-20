package com.example.weapon_microservice.model.manufacturer.mapper;

import com.example.weapon_microservice.model.manufacturer.WeaponManufacturerModel;
import com.example.weapon_microservice.model.manufacturer.dto.ManufacturerRequest;
import com.example.weapon_microservice.model.manufacturer.dto.WeaponManufacturerResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ManufacturerMapper {
    public static WeaponManufacturerModel modelFromRequest(ManufacturerRequest request, String id, String reference){
        return WeaponManufacturerModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .country(request.getCountry())
                .logo(request.getLogo())
                .build();
    }

    public static WeaponManufacturerResponse responseFromModel(WeaponManufacturerModel manufacturer) {
        return WeaponManufacturerResponse.builder()
                .id(manufacturer.getId())
                .reference(manufacturer.getReference())
                .name(manufacturer.getName())
                .country(manufacturer.getCountry())
                .logo(manufacturer.getLogo())
                .build();
    }

    public static List<WeaponManufacturerResponse> responseFromModelList(List<WeaponManufacturerModel> allWeaponManufacturers) {
        return allWeaponManufacturers.stream()
                .map(ManufacturerMapper::responseFromModel)
                .collect(Collectors.toList());
    }
}
