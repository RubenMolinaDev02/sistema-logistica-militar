package com.example.weapon_microservice.model.stock.dto;

import com.example.weapon_microservice.model.common.enums.StockAtachmentSystem;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.stock.enums.StockMaterial;
import com.example.weapon_microservice.model.stock.enums.StockType;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import lombok.Getter;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
@Getter
public class StockRequest {
    @NotBlank
    private String name;
    @NotNull
    private String image;
    @NotNull
    private StockMaterial material;
    @NotNull
    private StockAtachmentSystem atachmentMethod;
    @NotNull
    private List<String> compatiblePlatformsIds;
    @NotNull
    private StockType type;
}
