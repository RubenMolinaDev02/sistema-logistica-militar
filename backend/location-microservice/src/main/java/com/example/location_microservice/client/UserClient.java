package com.example.location_microservice.client;

import com.example.location_microservice.config.FeignConfig;
import com.example.location_microservice.model.location.dto.LocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-microservice", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/organization/locations/{id}")
    LocationResponse getById(@PathVariable String id);

    @GetMapping("/organization/locations/reference/{reference}")
    LocationResponse getByReference(@PathVariable String reference);

    @GetMapping("/organization/locations")
    List<LocationResponse> getAll();

    @GetMapping("/organization/locations/ids")
    List<LocationResponse> getAllById(@RequestBody List<String> ids);
}

