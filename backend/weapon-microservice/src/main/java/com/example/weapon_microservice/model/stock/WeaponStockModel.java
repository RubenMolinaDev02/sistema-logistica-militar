package com.example.weapon_microservice.model.stock;

import com.example.weapon_microservice.model.common.enums.StockAtachmentSystem;
import com.example.weapon_microservice.model.stock.dto.StockRequest;
import com.example.weapon_microservice.model.stock.enums.StockMaterial;
import com.example.weapon_microservice.model.stock.enums.StockType;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(value = "weapon-stock")
@Builder
@Getter
@Setter
public class WeaponStockModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private StockMaterial material;
    private StockAtachmentSystem atachmentMethod;
    private List<String> compatiblePlatformsIds;
    private StockType type;
}
