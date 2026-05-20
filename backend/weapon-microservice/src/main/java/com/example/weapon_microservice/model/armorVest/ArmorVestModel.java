package com.example.weapon_microservice.model.armorVest;

import com.example.weapon_microservice.model.armorVest.enums.SoftArmorLevel;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "vest")
@Builder
@Getter
@Setter
public class ArmorVestModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private SoftArmorLevel softArmor;
    private boolean compatibleWithPlates;
    private ServiceStatus status;
}
