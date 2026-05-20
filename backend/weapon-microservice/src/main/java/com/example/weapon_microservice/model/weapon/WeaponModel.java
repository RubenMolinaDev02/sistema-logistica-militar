package com.example.weapon_microservice.model.weapon;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.common.enums.SightMountType;
import com.example.weapon_microservice.model.common.enums.StockAtachmentSystem;
import com.example.weapon_microservice.model.weapon.dto.WeaponRequest;
import com.example.weapon_microservice.model.weapon.enums.ActionType;
import com.example.weapon_microservice.model.weapon.enums.FireMode;
import com.example.weapon_microservice.model.weapon.enums.WeaponType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "weapon")
@Builder
@Getter
@Setter
public class WeaponModel {
    //Weapon details
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private List<WeaponType> type;
    private String platformId;
    private int effectiveDistance;
    private double fireRate;
    private double barrelLength;
    private List<FireMode> fireMode;
    private ActionType action;
    private ServiceStatus status;

    //Caliber info
    private String caliberId;

    //Attachment mounts
    private SightMountType sightMount;
    private String handguardId;
    private boolean hasBayonetMount;
    private StockAtachmentSystem stockAttachmentSystem;
    private String stockId;
    private boolean compatibleWithSupressor;

    //Manufacturer
    private String manufacturerId;
}
