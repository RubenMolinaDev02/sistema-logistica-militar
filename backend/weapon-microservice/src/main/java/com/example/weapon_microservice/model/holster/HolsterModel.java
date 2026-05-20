package com.example.weapon_microservice.model.holster;

import com.example.weapon_microservice.model.holster.enums.HolsterType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "holster")
@Builder
@Getter
@Setter
public class HolsterModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private HolsterType type;
    private List<String> compatibleWeaponIds;
    private boolean universal;
}
