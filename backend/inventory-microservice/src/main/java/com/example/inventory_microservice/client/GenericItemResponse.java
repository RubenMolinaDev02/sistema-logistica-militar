package com.example.inventory_microservice.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenericItemResponse {
    private String id;
    private String name;
    private String itemType;
    private String reference;
    private String image;
}
