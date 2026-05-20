package com.example.weapon_microservice.model.stock.mapper;

import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.stock.dto.StockRequest;
import com.example.weapon_microservice.model.stock.dto.WeaponStockResponse;

import java.util.List;
import java.util.stream.Collectors;

public class StockMapper {
    public static WeaponStockModel modelFromRequest(StockRequest stock, String id, String reference) {
        return WeaponStockModel.builder()
                .id(id)
                .reference(reference)
                .name(stock.getName())
                .image(stock.getImage())
                .material(stock.getMaterial())
                .atachmentMethod(stock.getAtachmentMethod())
                .compatiblePlatformsIds(stock.getCompatiblePlatformsIds())
                .type(stock.getType())
                .build();
    }

    public static WeaponStockResponse responseFromModelSimple(WeaponStockModel stock) {
        return WeaponStockResponse.builder()
                .id(stock.getId())
                .reference(stock.getReference())
                .name(stock.getName())
                .image(stock.getImage())
                .material(stock.getMaterial())
                .atachmentMethod(stock.getAtachmentMethod())
                .type(stock.getType())
                .build();
    }

    public static WeaponStockResponse responseFromModel(WeaponStockModel stock, List<PlatformResponse> platformResponses) {
        return WeaponStockResponse.builder()
                .id(stock.getId())
                .reference(stock.getReference())
                .name(stock.getName())
                .image(stock.getImage())
                .material(stock.getMaterial())
                .atachmentMethod(stock.getAtachmentMethod())
                .type(stock.getType())
                .compatiblePlatforms(platformResponses)
                .build();
    }

    public static List<WeaponStockResponse> responseFromModelList(List<WeaponStockModel> stocks){
        return stocks.stream()
                .map(StockMapper::responseFromModelSimple)
                .collect(Collectors.toList());
    }
}
