package com.example.unit_microservice.model.unit.dto;

import com.example.unit_microservice.model.unit.UnitModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UnitNode {
    private UnitModel unit;
    private List<UnitNode> children;
}