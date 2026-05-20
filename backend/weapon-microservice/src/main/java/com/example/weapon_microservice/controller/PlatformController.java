package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.platform.dto.PlatformRequest;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.platform.dto.PlatformUpdateRequest;
import com.example.weapon_microservice.model.platform.mapper.PlatformMapper;
import com.example.weapon_microservice.service.platform.PlatformService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/platforms")
public class PlatformController {
    @Autowired
    private PlatformService service;

    @GetMapping
    public List<PlatformResponse> getAllPlatforms(){
        return PlatformMapper.responseFromModelList(service.getAllPlatforms());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public PlatformResponse createPlatform(@Valid @RequestBody PlatformRequest platform){
        return PlatformMapper.responseFromModel(service.savePlatform(platform));
    }

    @GetMapping("/reference/{reference}")
    public PlatformResponse getPlatformByReference(@PathVariable String reference){
        return PlatformMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping("/id/{id}")
    public PlatformResponse getPlatformById(@PathVariable String id){
        return PlatformMapper.responseFromModel(service.getPlatformById(id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public PlatformResponse updatePlatform(@PathVariable String id, @RequestBody PlatformUpdateRequest platform){
        return PlatformMapper.responseFromModel(service.updatePlatform(platform, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deletePlatform(@PathVariable String id){
        service.deletePlatform(id);
    }
}
