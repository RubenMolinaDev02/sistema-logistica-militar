package com.example.weapon_microservice.model.stock.dto;

import com.example.weapon_microservice.model.common.enums.StockAtachmentSystem;
import com.example.weapon_microservice.model.stock.enums.StockMaterial;
import com.example.weapon_microservice.model.stock.enums.StockType;
import lombok.Data;

import java.util.List;
@Data
public class StockUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private StockMaterial material;
    private StockAtachmentSystem atachmentMethod;
    private List<String> compatiblePlatformsIds;
    private StockType type;
}
