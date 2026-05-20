package com.example.weapon_microservice.model.bayonet.mapper;

import com.example.weapon_microservice.model.bayonet.BayonetModel;
import com.example.weapon_microservice.model.bayonet.dto.BayonetRequest;
import com.example.weapon_microservice.model.bayonet.dto.BayonetResponse;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;
import com.example.weapon_microservice.model.weapon.mapper.WeaponMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BayonetMapper {
    public static BayonetResponse responseFromModel(BayonetModel bayonetModel, List<WeaponModel> weaponModels){
        List<WeaponResponse> weaponResponses = WeaponMapper.responseFromModelSimpleList(weaponModels);
        return BayonetResponse.builder()
                .id(bayonetModel.getId())
                .reference(bayonetModel.getReference())
                .name(bayonetModel.getName())
                .image(bayonetModel.getImage())
                .bladeLength(bayonetModel.getBladeLength())
                .type(bayonetModel.getType())
                .compatibleWeapons(weaponResponses)
                .build();
    }

    public static BayonetResponse responseFromModelSimple(BayonetModel bayonetModel){
        return BayonetResponse.builder()
                .id(bayonetModel.getId())
                .reference(bayonetModel.getReference())
                .name(bayonetModel.getName())
                .image(bayonetModel.getImage())
                .bladeLength(bayonetModel.getBladeLength())
                .type(bayonetModel.getType())
                .build();
    }

    public static List<BayonetResponse> responseFromModelListSimple(List<BayonetModel> bayonetModels){
        return bayonetModels.stream()
                .map(BayonetMapper::responseFromModelSimple)
                .collect(Collectors.toList());
    }

    public static BayonetModel modelFromRequest(BayonetRequest request, String id, String reference){
        return BayonetModel.builder()
                .id(id)
                .reference(reference)
                .name(request.getName())
                .image(request.getImage())
                .bladeLength(request.getBladeLength())
                .type(request.getType())
                .compatibleWeaponsIds(request.getCompatibleWeaponsIds())
                .build();
    }
}
