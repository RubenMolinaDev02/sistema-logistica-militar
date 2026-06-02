package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.optic.OpticModel;
import com.example.weapon_microservice.model.optic.dto.OpticRequest;
import com.example.weapon_microservice.model.optic.dto.OpticResponse;
import com.example.weapon_microservice.model.optic.dto.OpticUpdateRequest;
import com.example.weapon_microservice.model.optic.mapper.OpticMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.model.textile.dto.TextileResponse;
import com.example.weapon_microservice.model.textile.mapper.TextileMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.optic.OpticService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/optics")
public class OpticController {
    @Autowired
    private OpticService service;

    @GetMapping
    public List<OpticResponse> getAllOptics(){
        return OpticMapper.responseFromModelList(service.getAllOptics());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public OpticResponse createOptic(@Valid @RequestBody OpticRequest Optic){
        return OpticMapper.responseFromModel(service.saveOptic(Optic));
    }

    @GetMapping("/model/{id}")
    public OpticModel getOpticByIdModel(@PathVariable String id){
        return service.getOpticById(id);
    }

    @GetMapping("/reference/{reference}")
    public OpticResponse getOpticByReference(@PathVariable String reference){
        return OpticMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping("/id/{id}")
    public OpticResponse getOpticById(@PathVariable String id){
        return OpticMapper.responseFromModel(service.getOpticById(id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public OpticResponse updateOptic(@PathVariable String id, @RequestBody OpticUpdateRequest Optic){
        return OpticMapper.responseFromModel(service.updateOptic(Optic, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteOptic(@PathVariable String id){
        service.deleteOptic(id);
    }

    @PostMapping("/search")
    public PageResponse<OpticResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<OpticModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<OpticResponse> pageToResponse(PageResponse<OpticModel> page){
        return PageResponse.<OpticResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(OpticMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
