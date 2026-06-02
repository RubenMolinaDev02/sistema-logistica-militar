package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.gasMaskFilter.GasMaskFilterModel;
import com.example.weapon_microservice.model.gasMaskFilter.dto.GasMaskFilterRequest;
import com.example.weapon_microservice.model.gasMaskFilter.dto.GasMaskFilterResponse;
import com.example.weapon_microservice.model.gasMaskFilter.dto.GasMaskFilterUpdateRequest;
import com.example.weapon_microservice.model.gasMaskFilter.mapper.GasMaskFilterMapper;
import com.example.weapon_microservice.model.grenade.GrenadeModel;
import com.example.weapon_microservice.model.grenade.dto.GrenadeResponse;
import com.example.weapon_microservice.model.grenade.mapper.GrenadeMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.gasMaskFilter.GasMaskFilterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/gas-mask-filter")
public class GasMaskFilterController {
    @Autowired
    private GasMaskFilterService service;

    @GetMapping
    public List<GasMaskFilterResponse> getAllGasMaskFilters(){
        return GasMaskFilterMapper.responseFromModelList(service.getAllGasMaskFilters());
    }

    @GetMapping("/model/{id}")
    public GasMaskFilterModel getGasMaskFilterByIdModel(@PathVariable String id){
        return service.getGasMaskFilterById(id);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public GasMaskFilterResponse createGasMaskFilter(@Valid @RequestBody GasMaskFilterRequest gasMaskFilter){
        return GasMaskFilterMapper.responseFromModel(service.saveGasMaskFilter(gasMaskFilter));
    }

    @GetMapping("/reference/{reference}")
    public GasMaskFilterResponse getGasMaskFilterByReference(@PathVariable String reference){
        return GasMaskFilterMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping("/id/{id}")
    public GasMaskFilterResponse getGasMaskFilterById(@PathVariable String id){
        return GasMaskFilterMapper.responseFromModel(service.getGasMaskFilterById(id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public GasMaskFilterResponse updateGasMaskFilter(@PathVariable String id, @RequestBody GasMaskFilterUpdateRequest gasMaskFilter){
        return GasMaskFilterMapper.responseFromModel(service.updateGasMaskFilter(gasMaskFilter, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteGasMaskFilter(@PathVariable String id){
        service.deleteGasMaskFilter(id);
    }

    @PostMapping("/search")
    public PageResponse<GasMaskFilterResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<GasMaskFilterModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<GasMaskFilterResponse> pageToResponse(PageResponse<GasMaskFilterModel> page){
        return PageResponse.<GasMaskFilterResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(GasMaskFilterMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
