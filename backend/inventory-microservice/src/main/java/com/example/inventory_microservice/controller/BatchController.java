package com.example.inventory_microservice.controller;

import com.example.inventory_microservice.model.batch.batchModel.BatchModel;
import com.example.inventory_microservice.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory/batches")
public class BatchController {

    @Autowired
    private BatchService service;

    @GetMapping("/id/{id}")
    public BatchModel getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/shipment/{shipmentId}")
    public List<BatchModel> getByShipment(@PathVariable String shipmentId) {
        return service.getByShipmentId(shipmentId);
    }

    @GetMapping("/ref/{ref}")
    public BatchModel getByReference(@PathVariable String ref){
        return service.getByReference(ref);
    }
}