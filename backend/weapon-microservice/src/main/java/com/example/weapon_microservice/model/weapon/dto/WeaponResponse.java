package com.example.weapon_microservice.model.weapon.dto;

import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.handguard.dto.HandguardResponse;
import com.example.weapon_microservice.model.manufacturer.WeaponManufacturerModel;
import com.example.weapon_microservice.model.manufacturer.dto.WeaponManufacturerResponse;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.stock.dto.WeaponStockResponse;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.enums.ActionType;
import com.example.weapon_microservice.model.weapon.enums.FireMode;
import com.example.weapon_microservice.model.weapon.enums.WeaponType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@JsonPropertyOrder({
        "id",
        "reference",
        "name",
        "image",
        "type",
        "platform",
        "effectiveDistance",
        "fireRate",
        "barrelLength",
        "fireMode",
        "action",
        "caliber",
        "handguard",
        "stock",
        "manufacturer",
        "status"
})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Builder
public class WeaponResponse {

    private String id;
    private String reference;
    private String name;
    private String image;
    private ServiceStatus status;

    private List<WeaponType> type;
    private PlatformResponse platform;

    private int effectiveDistance;
    private double fireRate;
    private double barrelLength;

    private List<FireMode> fireMode;
    private ActionType action;

    private CaliberResponse caliber;
    private WeaponManufacturerResponse manufacturer;

    private WeaponStockResponse stock;
    private HandguardResponse handguard;

    private boolean hasBayonetMount;
    private boolean compatibleWithSupressor;


}