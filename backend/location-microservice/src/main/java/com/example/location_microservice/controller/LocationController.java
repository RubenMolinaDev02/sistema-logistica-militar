package com.example.location_microservice.controller;

import com.example.location_microservice.model.PageResponse;
import com.example.location_microservice.model.location.LocationModel;
import com.example.location_microservice.model.location.dto.LocationRequest;
import com.example.location_microservice.model.location.dto.LocationResponse;
import com.example.location_microservice.model.location.dto.LocationUpdateRequest;
import com.example.location_microservice.model.location.mapper.LocationMapper;
import com.example.location_microservice.service.LocationService;
import com.example.location_microservice.service.SearchRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization/locations")
public class LocationController {

    @Autowired
    private LocationService service;

    @GetMapping("/{id}")
    public LocationResponse getById(@PathVariable String id) {
        return LocationMapper.responseFromModel(service.getLocationById(id));
    }

    @GetMapping("/reference/{reference}")
    public LocationResponse getByReference(@PathVariable String reference) {
        return LocationMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping
    public List<LocationResponse> getAllLocations() {
        return LocationMapper.responseFromModelList(service.getAllLocation());
    }

    @GetMapping("/ids")
    public List<LocationResponse> getAllById(@RequestBody List<String> ids){
        return LocationMapper.responseFromModelList(service.getAllByIds(ids));
    }

    @PostMapping
    public LocationResponse create(@Valid @RequestBody LocationRequest request) {
        return LocationMapper.responseFromModel(service.saveLocation(request));
    }

    @PatchMapping("/{id}")
    public LocationResponse modify(
            @RequestBody LocationUpdateRequest request,
            @PathVariable String id
    ) {
        return LocationMapper.responseFromModel(service.updateLocation(request, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteLocation(id);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping("/search")
    public PageResponse<LocationResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<LocationModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<LocationResponse> pageToResponse(PageResponse<LocationModel> page){
        return PageResponse.<LocationResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(LocationMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}