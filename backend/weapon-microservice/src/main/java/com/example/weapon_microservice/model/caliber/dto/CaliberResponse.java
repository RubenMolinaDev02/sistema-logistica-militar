package com.example.weapon_microservice.model.caliber.dto;

import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class CaliberResponse {
    private String id;
    private String reference;
    private String name;
    private String type;
    private String standard;
}
