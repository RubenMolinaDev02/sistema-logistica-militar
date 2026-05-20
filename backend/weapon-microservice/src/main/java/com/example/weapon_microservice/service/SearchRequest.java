package com.example.weapon_microservice.service;

import lombok.Getter;

import java.util.List;
@Getter
public class SearchRequest {

    private List<FilterCriteria> filters;

    private String sortBy;
    private String direction;
}
