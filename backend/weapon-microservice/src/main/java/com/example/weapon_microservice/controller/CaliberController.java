package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.caliber.dto.CaliberRequest;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.caliber.dto.CaliberUpdateRequest;
import com.example.weapon_microservice.model.caliber.mapper.CaliberMapper;
import com.example.weapon_microservice.service.caliber.CaliberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/calibers")
public class CaliberController {
    @Autowired
    private CaliberService service;

    @GetMapping
    public List<CaliberResponse> getAllCalibers(){
        return CaliberMapper.responseFromModelList(service.getAllCalibers());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public CaliberResponse createCaliber(@Valid @RequestBody CaliberRequest caliber){
        return CaliberMapper.responseFromModel(service.saveCaliber(caliber));
    }

    @GetMapping("/reference/{reference}")
    public CaliberResponse getCaliberByReference(@PathVariable String reference){
        return CaliberMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping("/id/{id}")
    public CaliberResponse getCaliberById(@PathVariable String id){
        return CaliberMapper.responseFromModel(service.getCaliberById(id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public CaliberResponse updateCaliber(@PathVariable String id, @RequestBody CaliberUpdateRequest caliber){
        return CaliberMapper.responseFromModel(service.updateCaliber(caliber, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteCaliber(@PathVariable String id){
        service.deleteCaliber(id);
    }
}
