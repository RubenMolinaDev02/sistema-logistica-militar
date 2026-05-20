package com.example.weapon_microservice.model.genericItem.dto;

import com.example.weapon_microservice.model.genericItem.enums.ItemType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.Map;
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericItemResponse {
    private String id;
    private String name;
    private ItemType itemType;
    private String reference;
    private String image;
}
