package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.nvg.NvgModel;
import com.example.weapon_microservice.model.nvg.dto.NvgRequest;
import com.example.weapon_microservice.model.nvg.dto.NvgResponse;
import com.example.weapon_microservice.model.nvg.dto.NvgUpdateRequest;
import com.example.weapon_microservice.model.nvg.mapper.NvgMapper;
import com.example.weapon_microservice.service.nvg.NvgService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/nvg")
public class NvgController {
    @Autowired
    private NvgService service;

    @GetMapping
    public List<NvgResponse> getAllNvg(){
        return NvgMapper.responseFromModelList(service.getAllNvg());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public NvgResponse createNvg(@Valid @RequestBody NvgRequest nvg){
        NvgModel model = service.saveNvg(nvg);
        return NvgMapper.responseFromModel(model);
    }

    @GetMapping("/reference/{reference}")
    public NvgResponse getNvgByReference(@PathVariable String reference){
        NvgModel nvg = service.getByReference(reference);
        return NvgMapper.responseFromModel(nvg);
    }

    @GetMapping("/id/{id}")
    public NvgResponse getNvgById(@PathVariable String id){
        NvgModel nvg = service.getNvgById(id);
        return NvgMapper.responseFromModel(nvg);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public NvgResponse updateNvg(@PathVariable String id, @RequestBody NvgUpdateRequest Nvg){
        NvgModel nvg = service.updateNvg(Nvg, id);
        return NvgMapper.responseFromModel(nvg);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteNvg(@PathVariable String id){
        service.deleteNvg(id);
    }
}
