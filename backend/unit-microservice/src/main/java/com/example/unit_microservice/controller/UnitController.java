package com.example.unit_microservice.controller;

import com.example.unit_microservice.model.unit.dto.UnitNode;
import com.example.unit_microservice.model.unit.dto.UnitRequest;
import com.example.unit_microservice.model.unit.dto.UnitResponse;
import com.example.unit_microservice.model.unit.dto.UnitUpdateRequest;
import com.example.unit_microservice.model.unit.mapper.UnitMapper;
import com.example.unit_microservice.service.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {

    @Autowired
    private UnitService service;

    @GetMapping("/{id}")
    public UnitResponse getById(@PathVariable String id) {
        return UnitMapper.responseFromModel(service.getUnitById(id));
    }

    @GetMapping("/reference/{reference}")
    public UnitResponse getByReference(@PathVariable String reference) {
        return UnitMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping
    public List<UnitResponse> getAll() {
        return UnitMapper.responseFromModelList(service.getAllUnit());
    }

    @GetMapping("/{id}/children")
    public List<UnitResponse> getChildren(@PathVariable String id) {
        return UnitMapper.responseFromModelList(service.getUnitsByParentId(id));
    }

    @PostMapping
    public UnitResponse create(@RequestBody @Valid UnitRequest request) {
        return UnitMapper.responseFromModel(service.saveUnit(request));
    }

    @PatchMapping("/{id}")
    public UnitResponse update(
            @PathVariable String id,
            @RequestBody @Valid UnitUpdateRequest request
    ) {
        return UnitMapper.responseFromModel(service.updateUnit(request, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteUnit(id);
    }

    @GetMapping("/{id}/parents")
    public List<UnitResponse> getParents(@PathVariable String id) {
        return UnitMapper.responseFromModelList(service.getParents(id));
    }

    @GetMapping("/{id}/tree")
    public UnitNode getTree(@PathVariable String id) {
        return service.getTree(id);
    }
}