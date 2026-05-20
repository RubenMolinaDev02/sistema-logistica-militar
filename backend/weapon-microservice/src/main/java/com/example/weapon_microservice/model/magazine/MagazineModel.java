package com.example.weapon_microservice.model.magazine;

import java.util.List;

import com.example.weapon_microservice.model.magazine.dto.MagazineRequest;
import com.example.weapon_microservice.model.magazine.enums.MagazineType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "magazine")
@Getter
@Builder
@Setter
public class MagazineModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private List<String> compatiblePlatformsIds;
    private String caliberId;
    private int capacity;
    private MagazineType type;
}
