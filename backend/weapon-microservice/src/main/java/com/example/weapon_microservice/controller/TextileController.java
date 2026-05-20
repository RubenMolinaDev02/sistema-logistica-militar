package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.model.textile.dto.TextileRequest;
import com.example.weapon_microservice.model.textile.dto.TextileResponse;
import com.example.weapon_microservice.model.textile.dto.TextileUpdateRequest;
import com.example.weapon_microservice.model.textile.mapper.TextileMapper;
import com.example.weapon_microservice.service.textile.TextileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/textile")
public class TextileController {
    @Autowired
    private TextileService service;

    @GetMapping
    public List<TextileResponse> getAllTextile(){
        return TextileMapper.responseFromModelList(service.getAllTextile());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public TextileResponse createTextile(@Valid @RequestBody TextileRequest textile){
        TextileModel model = service.saveTextile(textile);
        return TextileMapper.responseFromModel(model);
    }

    @GetMapping("/reference/{reference}")
    public TextileResponse getTextileByReference(@PathVariable String reference){
        TextileModel textile = service.getByReference(reference);
        return TextileMapper.responseFromModel(textile);
    }

    @GetMapping("/id/{id}")
    public TextileResponse getTextileById(@PathVariable String id){
        TextileModel textile = service.getTextileById(id);
        return TextileMapper.responseFromModel(textile);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public TextileResponse updateTextile(@PathVariable String id, @RequestBody TextileUpdateRequest Textile){
        TextileModel textile = service.updateTextile(Textile, id);
        return TextileMapper.responseFromModel(textile);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteTextile(@PathVariable String id){
        service.deleteTextile(id);
    }
}
