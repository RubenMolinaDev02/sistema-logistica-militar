package com.example.weapon_microservice.model.barrelAtachment;

import java.util.List;

import com.example.weapon_microservice.model.barrelAtachment.enums.BarrelAttachmentType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "barrel-atachment")
@Builder
@Getter
@Setter
public class BarrelAtachmentModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private BarrelAttachmentType type;
    private List<String> compatiblePlatformsIds;
    private String compatibleCaliber;
}
