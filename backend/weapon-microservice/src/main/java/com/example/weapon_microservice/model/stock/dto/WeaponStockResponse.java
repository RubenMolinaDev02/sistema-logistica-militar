package com.example.weapon_microservice.model.stock.dto;

import com.example.weapon_microservice.model.common.enums.StockAtachmentSystem;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.stock.enums.StockMaterial;
import com.example.weapon_microservice.model.stock.enums.StockType;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class WeaponStockResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private StockMaterial material;
    private StockAtachmentSystem atachmentMethod;
    private StockType type;
    private List<PlatformResponse> compatiblePlatforms;
}
