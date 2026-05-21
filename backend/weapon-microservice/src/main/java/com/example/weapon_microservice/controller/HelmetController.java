package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.helmet.HelmetModel;
import com.example.weapon_microservice.model.helmet.dto.HelmetRequest;
import com.example.weapon_microservice.model.helmet.dto.HelmetResponse;
import com.example.weapon_microservice.model.helmet.dto.HelmetUpdateRequest;
import com.example.weapon_microservice.model.helmet.mapper.HelmetMapper;
import com.example.weapon_microservice.model.holster.HolsterModel;
import com.example.weapon_microservice.model.holster.dto.HolsterResponse;
import com.example.weapon_microservice.model.holster.mapper.HolsterMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.helmet.HelmetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/helmet")
public class HelmetController {
    @Autowired
    private HelmetService service;

    @GetMapping
    public List<HelmetResponse> getAllHelmet(){
        return HelmetMapper.responseFromModelList(service.getAllHelmet());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public HelmetResponse createHelmet(@Valid @RequestBody HelmetRequest helmet){
        HelmetModel model = service.saveHelmet(helmet);
        return HelmetMapper.responseFromModel(model);
    }

    @GetMapping("/reference/{reference}")
    public HelmetResponse getHelmetByReference(@PathVariable String reference){
        HelmetModel helmet = service.getHelmetByReference(reference);
        return HelmetMapper.responseFromModel(helmet);
    }

    @GetMapping("/id/{id}")
    public HelmetResponse getHelmetById(@PathVariable String id){
        HelmetModel helmet = service.getHelmetById(id);
        return HelmetMapper.responseFromModel(helmet);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public HelmetResponse updateHelmet(@PathVariable String id, @RequestBody HelmetUpdateRequest helmetUpdateRequest){
        HelmetModel helmet = service.updateHelmet(helmetUpdateRequest, id);
        return HelmetMapper.responseFromModel(helmet);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteHelmet(@PathVariable String id){
        service.deleteHelmet(id);
    }

    @PostMapping("/search")
    public PageResponse<HelmetResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<HelmetModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<HelmetResponse> pageToResponse(PageResponse<HelmetModel> page){
        return PageResponse.<HelmetResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(HelmetMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
