package com.example.weapon_microservice.model.weapon.dto;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.common.enums.SightMountType;
import com.example.weapon_microservice.model.common.enums.StockAtachmentSystem;
import com.example.weapon_microservice.model.weapon.enums.ActionType;
import com.example.weapon_microservice.model.weapon.enums.FireMode;
import com.example.weapon_microservice.model.weapon.enums.WeaponType;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.List;

@Getter
public class WeaponRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private String image;

    @NotEmpty
    private List<WeaponType> type;
    @NotBlank
    private String platformId;

    @Positive
    private int effectiveDistance;
    @Positive
    private double fireRate;
    @PositiveOrZero
    private double barrelLength;

    @NotEmpty
    private List<FireMode> fireMode;
    @NotNull
    private ActionType action;

    @NotBlank
    private String caliberId;

    @NotNull
    private SightMountType sightMount;
    private String handguardId;

    private boolean hasBayonetMount;
    @NotNull
    private StockAtachmentSystem stockAttachmentSystem;
    @NotNull
    private ServiceStatus status;
    private String stockId;

    private boolean compatibleWithSupressor;

    @NotBlank
    private String manufacturerId;
}