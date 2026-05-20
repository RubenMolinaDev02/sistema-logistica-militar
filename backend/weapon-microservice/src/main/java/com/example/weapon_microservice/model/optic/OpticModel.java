package com.example.weapon_microservice.model.optic;

import com.example.weapon_microservice.model.common.enums.SightMountType;
import com.example.weapon_microservice.model.optic.enums.OpticType;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "optic")
@Builder
@Getter
@Setter
public class OpticModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private SightMountType mountType;
    private OpticType type;
    private int minZoom;
    private int maxZoom;
}
