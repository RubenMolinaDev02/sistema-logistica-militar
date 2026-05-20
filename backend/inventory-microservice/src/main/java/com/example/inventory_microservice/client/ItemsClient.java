package com.example.inventory_microservice.client;

import com.example.inventory_microservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "weapon-microservice", configuration = FeignConfig.class)
public interface ItemsClient {

    @GetMapping("/items/id/{id}")
    LocationResponse getById(@PathVariable String id);

    @GetMapping("/items/ref/{ref}")
    LocationResponse getByReference(@PathVariable String ref);
}