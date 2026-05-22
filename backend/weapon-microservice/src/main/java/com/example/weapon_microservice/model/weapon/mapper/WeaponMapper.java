package com.example.weapon_microservice.model.weapon.mapper;

import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.caliber.mapper.CaliberMapper;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.handguard.dto.HandguardResponse;
import com.example.weapon_microservice.model.handguard.mapper.HandguardMapper;
import com.example.weapon_microservice.model.manufacturer.WeaponManufacturerModel;
import com.example.weapon_microservice.model.manufacturer.dto.WeaponManufacturerResponse;
import com.example.weapon_microservice.model.manufacturer.mapper.ManufacturerMapper;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.platform.mapper.PlatformMapper;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.stock.dto.WeaponStockResponse;
import com.example.weapon_microservice.model.stock.mapper.StockMapper;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.dto.WeaponRequest;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeaponMapper {
    public static WeaponModel modelFromRequest(WeaponRequest request, String id, String reference){
        return WeaponModel.builder()
                .id(id)
                .name(request.getName())
                .reference(reference)
                .image(request.getImage())
                .type(request.getType())
                .platformId(request.getPlatformId())
                .effectiveDistance(request.getEffectiveDistance())
                .fireRate(request.getFireRate())
                .barrelLength(request.getBarrelLength())
                .fireMode(request.getFireMode())
                .action(request.getAction())
                .caliberId(request.getCaliberId())
                .sightMount(request.getSightMount())
                .handguardId(request.getHandguardId())
                .hasBayonetMount(request.isHasBayonetMount())
                .stockAttachmentSystem(request.getStockAttachmentSystem())
                .stockId(request.getStockId())
                .compatibleWithSupressor(request.isCompatibleWithSupressor())
                .manufacturerId(request.getManufacturerId())
                .status(request.getStatus())
                .build();
    }
    public static WeaponResponse responseFromModel (WeaponModel weaponModel,
                                       PlatformModel platform,
                                       CaliberModel caliber,
                                       WeaponManufacturerModel manufacturer,
                                       WeaponStockModel stock,
                                       HandguardModel handguard){

        PlatformResponse platformResponse = PlatformMapper.responseFromModel(platform);
        CaliberResponse caliberResponse = CaliberMapper.responseFromModelSimple(caliber);
        WeaponManufacturerResponse manufacturerResponse = ManufacturerMapper.responseFromModel(manufacturer);

        WeaponStockResponse weaponStockResponse = null;
        if (weaponModel.getStockId() != null) weaponStockResponse = StockMapper.responseFromModelSimple(stock);

        HandguardResponse handguardResponse = null;
        if (weaponModel.getHandguardId() != null) HandguardMapper.responseFromModelSimple(handguard);

        return WeaponResponse.builder()
                .id(weaponModel.getId())
                .reference(weaponModel.getReference())
                .name(weaponModel.getName())
                .image(weaponModel.getImage())
                .type(weaponModel.getType())
                .platform(platformResponse)
                .effectiveDistance(weaponModel.getEffectiveDistance())
                .fireRate(weaponModel.getFireRate())
                .barrelLength(weaponModel.getBarrelLength())
                .fireMode(weaponModel.getFireMode())
                .action(weaponModel.getAction())
                .caliber(caliberResponse)
                .manufacturer(manufacturerResponse)
                .stock(weaponStockResponse)
                .handguard(handguardResponse)
                .hasBayonetMount(weaponModel.isHasBayonetMount())
                .compatibleWithSupressor(weaponModel.isCompatibleWithSupressor())
                .status(weaponModel.getStatus())
                .build();
    }

    public static WeaponResponse responseFromModelSimple (WeaponModel weaponModel){
        if (weaponModel == null) return null;
        return WeaponResponse.builder()
                .id(weaponModel.getId())
                .reference(weaponModel.getReference())
                .name(weaponModel.getName())
                .image(weaponModel.getImage())
                .type(weaponModel.getType())
                .effectiveDistance(weaponModel.getEffectiveDistance())
                .fireRate(weaponModel.getFireRate())
                .barrelLength(weaponModel.getBarrelLength())
                .fireMode(weaponModel.getFireMode())
                .action(weaponModel.getAction())
                .hasBayonetMount(weaponModel.isHasBayonetMount())
                .compatibleWithSupressor(weaponModel.isCompatibleWithSupressor())
                .status(weaponModel.getStatus())
                .build();
    }

    public static List<WeaponResponse> responseFromModelSimpleList (List<WeaponModel> weaponModels){
        if (weaponModels.isEmpty()) return new ArrayList<>();
        return weaponModels.stream()
                .map(WeaponMapper::responseFromModelSimple)
                .collect(Collectors.toList());
    }
}
