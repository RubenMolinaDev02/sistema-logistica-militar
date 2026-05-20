package com.example.weapon_microservice.model.textile;

import com.example.weapon_microservice.model.textile.enums.TextileType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "textile")
@Data
@Builder
public class TextileModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private TextileType type;
}
