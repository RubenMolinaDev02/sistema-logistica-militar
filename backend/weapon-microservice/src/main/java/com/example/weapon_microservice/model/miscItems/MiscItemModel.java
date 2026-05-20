package com.example.weapon_microservice.model.miscItems;

import com.example.weapon_microservice.model.miscItems.enums.MiscType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "miscItem")
@Builder
@Getter
@Setter
public class MiscItemModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private MiscType type;
    private String image;
}
