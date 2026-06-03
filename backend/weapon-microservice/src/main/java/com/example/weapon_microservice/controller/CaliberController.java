package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.ammo.AmmoModel;
import com.example.weapon_microservice.model.ammo.dto.AmmoResponse;
import com.example.weapon_microservice.model.ammo.mapper.AmmoMapper;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.dto.CaliberRequest;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.caliber.dto.CaliberUpdateRequest;
import com.example.weapon_microservice.model.caliber.mapper.CaliberMapper;
import com.example.weapon_microservice.model.gasMaskFilter.GasMaskFilterModel;
import com.example.weapon_microservice.model.gasMaskFilter.dto.GasMaskFilterResponse;
import com.example.weapon_microservice.model.gasMaskFilter.mapper.GasMaskFilterMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.ammo.AmmoService;
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
    @Autowired
    private AmmoService ammoService;

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping
    public List<CaliberResponse> getAllCalibers(){
        return CaliberMapper.responseFromModelList(service.getAllCalibers());
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/model/{id}")
    public CaliberModel getCaliberByIdModel(@PathVariable String id){
        return service.getCaliberById(id);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PostMapping
    public CaliberResponse createCaliber(@Valid @RequestBody CaliberRequest caliber){
        CaliberModel caliberModel = service.saveCaliber(caliber);
        List<AmmoResponse> ammo = AmmoMapper.responseFromModelListSimple(ammoService.getAmmoByCaliberId(caliberModel.getId()));

        return CaliberMapper.responseFromModel(caliberModel, ammo);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/reference/{reference}")
    public CaliberResponse getCaliberByReference(@PathVariable String reference){
        CaliberModel caliberModel = service.getByReference(reference);
        List<AmmoResponse> ammo = AmmoMapper.responseFromModelListSimple(ammoService.getAmmoByCaliberId(caliberModel.getId()));

        return CaliberMapper.responseFromModel(caliberModel, ammo);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/id/{id}")
    public CaliberResponse getCaliberById(@PathVariable String id){
        CaliberModel caliberModel = service.getCaliberById(id);
        List<AmmoResponse> ammo = AmmoMapper.responseFromModelListSimple(ammoService.getAmmoByCaliberId(caliberModel.getId()));

        return CaliberMapper.responseFromModel(caliberModel, ammo);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PatchMapping("/{id}")
    public CaliberResponse updateCaliber(@PathVariable String id, @RequestBody CaliberUpdateRequest caliber){
        CaliberModel caliberModel = service.updateCaliber(caliber, id);
        List<AmmoResponse> ammo = AmmoMapper.responseFromModelListSimple(ammoService.getAmmoByCaliberId(caliberModel.getId()));

        return CaliberMapper.responseFromModel(caliberModel, ammo);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @DeleteMapping("/{id}")
    public void deleteCaliber(@PathVariable String id){
        service.deleteCaliber(id);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @PostMapping("/search")
    public PageResponse<CaliberResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<CaliberModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<CaliberResponse> pageToResponse(PageResponse<CaliberModel> page){
        return PageResponse.<CaliberResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(CaliberMapper::responseFromModelSimple)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
