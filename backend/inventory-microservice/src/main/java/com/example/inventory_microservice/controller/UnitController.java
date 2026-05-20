package com.example.inventory_microservice.controller;

import com.example.inventory_microservice.model.unit.dto.UnitRequest;
import com.example.inventory_microservice.model.unit.dto.UnitResponse;
import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import com.example.inventory_microservice.model.unit.mapper.SerializedUnitMapper;
import com.example.inventory_microservice.service.UnitService;
import com.example.inventory_microservice.service.UnitTransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/serialized")
public class UnitController {

    @Autowired
    private UnitService service;

    @Autowired
    private UnitTransitionService transitionService;

    @GetMapping("/{id}")
    public UnitResponse getById(@PathVariable String id) {
        return SerializedUnitMapper.responseFromModel(service.getById(id));
    }

    @PostMapping
    public List<UnitResponse> create(@RequestBody UnitRequest request) {
        return SerializedUnitMapper.responseFromModelList(service.createUnits(request));
    }

    // --- PHYSICAL STATUS ---
    @PatchMapping("/status")
    public List<UnitResponse> changeStatus(
            @RequestParam List<String> ids,
            @RequestParam UnitStatus status
    ) {
        return SerializedUnitMapper.responseFromModelList(
                transitionService.changeStatusMultiple(ids, status)
        );
    }

    // --- LOGISTICAL STATUS ---
    @PatchMapping("/logistical")
    public List<UnitResponse> changeLogistical(
            @RequestParam List<String> ids,
            @RequestParam LogisticalStatus status
    ) {
        return SerializedUnitMapper.responseFromModelList(
                transitionService.changeLogisticalStatus(ids, status)
        );
    }
}
