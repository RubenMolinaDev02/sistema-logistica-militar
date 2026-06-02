package com.example.user_microservice.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationResponse {
    private String id;
    private String reference;
    private String name;
    private String country;
    private String type;
    private int maxTroops;
    private String status;
}
