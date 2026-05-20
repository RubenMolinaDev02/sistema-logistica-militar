package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.grenade.dto.GrenadeRequest;
import com.example.weapon_microservice.model.grenade.dto.GrenadeResponse;
import com.example.weapon_microservice.model.grenade.dto.GrenadeUpdateRequest;
import com.example.weapon_microservice.model.grenade.mapper.GrenadeMapper;
import com.example.weapon_microservice.service.grenade.GrenadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/grenades")
public class GrenadeController {
    @Autowired
    private GrenadeService service;

    @GetMapping
    public List<GrenadeResponse> getAllGrenades(){
        return GrenadeMapper.responseFromModelList(service.getAllGrenades());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public GrenadeResponse createGrenade(@Valid @RequestBody GrenadeRequest grenade){
        return GrenadeMapper.responseFromModel(service.saveGrenade(grenade));
    }

    @GetMapping("/reference/{reference}")
    public GrenadeResponse getGrenadeByReference(@PathVariable String reference){
        return GrenadeMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping("/id/{id}")
    public GrenadeResponse getGrenadeById(@PathVariable String id){
        return GrenadeMapper.responseFromModel(service.getGrenadeById(id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public GrenadeResponse updateGrenade(@PathVariable String id, @RequestBody GrenadeUpdateRequest grenade){
        return GrenadeMapper.responseFromModel(service.updateGrenade(grenade, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteGrenade(@PathVariable String id){
        service.deleteGrenade(id);
    }
}
