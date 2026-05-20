package com.example.inventory_microservice.controller;

import com.example.inventory_microservice.model.sku.SkuModel;
import com.example.inventory_microservice.model.stock.dto.StockRequest;
import com.example.inventory_microservice.model.stock.dto.StockResponse;
import com.example.inventory_microservice.model.stock.mapper.StockMapper;
import com.example.inventory_microservice.service.SkuService;
import com.example.inventory_microservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/stock")
public class StockController {

    @Autowired
    private StockService service;

    @Autowired
    private SkuService skuService;

    @PostMapping
    public StockResponse increaseStock(@RequestBody StockRequest request) {
        SkuModel skuModel = skuService.getById(request.getSkuId());
        return StockMapper.responseFromModelSimple(
                service.registerStock(request.getLocationId(), skuModel, request.getUnits())
        );
    }

    @GetMapping("/sku/{skuId}")
    public List<StockResponse> getBySku(@PathVariable String skuId) {
        return service.getBySkuId(skuId)
                .stream()
                .map(StockMapper::responseFromModelSimple)
                .toList();
    }

    @GetMapping("/location/{locationId}")
    public List<StockResponse> getByLocation(@PathVariable String locationId) {
        return service.getByLocationId(locationId)
                .stream()
                .map(StockMapper::responseFromModelSimple)
                .toList();
    }

    @PatchMapping("/{id}/decrease")
    public StockResponse decrease(@PathVariable String id, @RequestParam int amount) {
        return StockMapper.responseFromModelSimple(service.decreaseStock(id, amount));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteStock(id);
    }
}
