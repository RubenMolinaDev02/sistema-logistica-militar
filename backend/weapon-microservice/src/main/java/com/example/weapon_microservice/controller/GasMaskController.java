package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.gasMask.dto.GasMaskRequest;
import com.example.weapon_microservice.model.gasMask.dto.GasMaskResponse;
import com.example.weapon_microservice.model.gasMask.dto.GasMaskUpdateRequest;
import com.example.weapon_microservice.model.gasMask.mapper.GasMaskMapper;
import com.example.weapon_microservice.service.gasMask.GasMaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/gas-mask")
public class GasMaskController {
    @Autowired
    private GasMaskService service;

    @GetMapping
    public List<GasMaskResponse> getAllGasMasks(){
        return GasMaskMapper.responseFromModelList(service.getAllGasMasks());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public GasMaskResponse createGasMask(@Valid @RequestBody GasMaskRequest gasMask){
        return GasMaskMapper.responseFromModel(service.saveGasMask(gasMask));
    }

    @GetMapping("/reference/{reference}")
    public GasMaskResponse getGasMaskByReference(@PathVariable String reference){
        return GasMaskMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping("/id/{id}")
    public GasMaskResponse getGasMaskById(@PathVariable String id){
        return GasMaskMapper.responseFromModel(service.getGasMaskById(id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public GasMaskResponse updateGasMask(@PathVariable String id, @RequestBody GasMaskUpdateRequest gasMask){
        return GasMaskMapper.responseFromModel(service.updateGasMask(gasMask, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteGasMask(@PathVariable String id){
        service.deleteGasMask(id);
    }
}
