package com.example.weapon_microservice.model.grenade;

import com.example.weapon_microservice.model.grenade.enums.ArmingMethod;
import com.example.weapon_microservice.model.grenade.enums.GrenadeType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "grenade")
@Data
@Builder
public class GrenadeModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private double fuzeDelay;
    private double armingDistance;
    private GrenadeType type;
    private ArmingMethod armingMethod;
    private boolean lethal;
}
