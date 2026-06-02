package com.example.user_microservice.client;

import com.example.user_microservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "location-microservice", configuration = FeignConfig.class)
public interface LocationClient {

    @GetMapping("/organization/locations/{id}")
    LocationResponse getById(@PathVariable String id);

    @GetMapping("/organization/locations/reference/{reference}")
    LocationResponse getByReference(@PathVariable String reference);

    @GetMapping("/organization/locations")
    List<LocationResponse> getAll();

    @GetMapping("/organization/locations/ids")
    List<LocationResponse> getAllById(@RequestBody List<String> ids);
}

