package com.example.weapon_microservice.model.weapon.dto;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.common.enums.SightMountType;
import com.example.weapon_microservice.model.common.enums.StockAtachmentSystem;
import com.example.weapon_microservice.model.weapon.enums.ActionType;
import com.example.weapon_microservice.model.weapon.enums.FireMode;
import com.example.weapon_microservice.model.weapon.enums.WeaponType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeaponUpdateRequest {
    private String name;
    private String reference;
    private String image;
    private List<WeaponType> type;
    private String platformId;
    private Integer effectiveDistance;
    private Double fireRate;
    private Double barrelLength;
    private List<FireMode> fireMode;

    private ActionType action;
    private String caliberId;

    private SightMountType sightMount;
    private String handguardId;

    private Boolean hasBayonetMount;

    private StockAtachmentSystem stockAtachmentSystem;

    private ServiceStatus status;
    private String stockId;

    private Boolean compatibleWithSupressor;
    private String manufacturerId;
}