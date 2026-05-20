package com.example.inventory_microservice.controller;

import com.example.inventory_microservice.model.sku.dto.SkuRequest;
import com.example.inventory_microservice.model.sku.dto.SkuResponse;
import com.example.inventory_microservice.model.sku.mapper.SkuMapper;
import com.example.inventory_microservice.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/sku")
public class SkuController {
    @Autowired
    private SkuService service;

    @GetMapping("/{id}")
    public SkuResponse getById(@PathVariable String id) {
        return SkuMapper.responseFromModelSimple(service.getById(id));
    }

    @GetMapping("/item/{itemId}")
    public List<SkuResponse> getByItemId(@PathVariable String itemId){
        return SkuMapper.responseFromModelListSimple(service.getByItemId(itemId));
    }

    @PostMapping
    public SkuResponse create(@RequestBody SkuRequest request) {
        return SkuMapper.responseFromModelSimple(service.createSku(request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteSku(id);
    }
}
