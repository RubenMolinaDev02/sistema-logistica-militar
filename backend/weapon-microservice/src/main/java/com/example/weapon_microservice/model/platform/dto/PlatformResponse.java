package com.example.weapon_microservice.model.platform.dto;

import com.example.weapon_microservice.model.platform.PlatformModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class PlatformResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private String description;
}
