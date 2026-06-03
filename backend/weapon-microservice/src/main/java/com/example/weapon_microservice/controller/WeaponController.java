package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.weapon.dto.WeaponRequest;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;
import com.example.weapon_microservice.model.weapon.dto.WeaponUpdateRequest;
import com.example.weapon_microservice.model.weapon.mapper.WeaponMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.weapon.WeaponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/weapons")
public class WeaponController {
    @Autowired
    private WeaponService weaponService;

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping
    public List<WeaponResponse> getAllWeapons(){
       return WeaponMapper.responseFromModelSimpleList(weaponService.getAllWeapons());
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/reference/{reference}")
    public WeaponResponse getWeaponByReference(@PathVariable String reference){
        return responseFromModel(weaponService.getByReference(reference));
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/id/{id}")
    public WeaponResponse getWeaponById(@PathVariable String id){
        return responseFromModel(weaponService.getWeaponById(id));
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/model/{id}")
    public WeaponModel getWeaponByIdModel(@PathVariable String id){
        return weaponService.getWeaponById(id);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PatchMapping("/{id}")
    public WeaponResponse modifyWeapon(@PathVariable String id, @RequestBody WeaponUpdateRequest request){
        return responseFromModel(weaponService.updateWeapon(request, id));
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @DeleteMapping("/{id}")
    public void deleteWeapon(@PathVariable String id){
        weaponService.deleteWeapon(id);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @PostMapping("/search")
    public PageResponse<WeaponResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<WeaponModel> result = weaponService.search(request, page, size);
        return pageToResponse(result);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PostMapping
    public WeaponResponse createWeapon(@Valid @RequestBody WeaponRequest weapon){
        return responseFromModel(weaponService.saveWeapon(weapon));
    }

    private WeaponResponse responseFromModel(WeaponModel weapon){
        WeaponStockModel stock = null;
        if (weapon.getStockId() != null) stock = weaponService.getStockById(weapon.getStockId());
        HandguardModel handguard = null;
        if (weapon.getHandguardId() != null) handguard = weaponService.getHandguardById(weapon.getHandguardId());

        return WeaponMapper.responseFromModel(
                weapon,
                weaponService.getPlatformById(weapon.getPlatformId()),
                weaponService.getCaliberById(weapon.getCaliberId()),
                weaponService.getManufacturerById(weapon.getManufacturerId()),
                stock,
                handguard
        );
    }

    public PageResponse<WeaponResponse> pageToResponse(PageResponse<WeaponModel> page){
        return PageResponse.<WeaponResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(WeaponMapper::responseFromModelSimple)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }

}