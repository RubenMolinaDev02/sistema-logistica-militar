package com.example.weapon_microservice.model.miscItems.dto;

import com.example.weapon_microservice.model.miscItems.enums.MiscType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class MiscItemResponse {
    private String id;
    private String reference;
    private String name;
    private MiscType type;
    private String image;
}
