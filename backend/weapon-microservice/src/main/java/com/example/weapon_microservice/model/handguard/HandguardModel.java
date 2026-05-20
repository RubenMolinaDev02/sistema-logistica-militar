package com.example.weapon_microservice.model.handguard;

import java.util.List;

import com.example.weapon_microservice.model.common.enums.MountType;
import com.example.weapon_microservice.model.handguard.dto.HandguardRequest;
import com.example.weapon_microservice.model.handguard.enums.HandguardMaterial;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "handguard")
@Builder
@Getter
@Setter
public class HandguardModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private MountType mountType;
    private HandguardMaterial material;
    private List<String> compatiblePlatformsIds;
}
