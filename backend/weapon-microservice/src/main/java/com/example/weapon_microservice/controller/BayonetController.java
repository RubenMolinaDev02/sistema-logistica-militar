package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.bayonet.BayonetModel;
import com.example.weapon_microservice.model.bayonet.dto.BayonetRequest;
import com.example.weapon_microservice.model.bayonet.dto.BayonetResponse;
import com.example.weapon_microservice.model.bayonet.dto.BayonetUpdateRequest;
import com.example.weapon_microservice.model.bayonet.mapper.BayonetMapper;
import com.example.weapon_microservice.model.gasMaskFilter.GasMaskFilterModel;
import com.example.weapon_microservice.model.gasMaskFilter.dto.GasMaskFilterResponse;
import com.example.weapon_microservice.model.gasMaskFilter.mapper.GasMaskFilterMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.bayonet.BayonetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/bayonets")
public class BayonetController {
    @Autowired
    private BayonetService service;

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping
    public List<BayonetResponse> getAllBayonets(){
        return BayonetMapper.responseFromModelListSimple(service.getAllBayonets());
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/model/{id}")
    public BayonetModel getBayonetByIdModel(@PathVariable String id){
        return service.getBayonetById(id);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PostMapping
    public BayonetResponse createBayonet(@Valid @RequestBody BayonetRequest bayonet){
        return BayonetMapper.responseFromModel(service.saveBayonet(bayonet), service.getWeaponsByIds(bayonet.getCompatibleWeaponsIds()));
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/reference/{reference}")
    public BayonetResponse getBayonetByReference(@PathVariable String reference){
        BayonetModel model = service.getByReference(reference);
        return BayonetMapper.responseFromModel(model, service.getWeaponsByIds(model.getCompatibleWeaponsIds()));
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/id/{id}")
    public BayonetResponse getBayonetById(@PathVariable String id){
        BayonetModel model = service.getBayonetById(id);
        return BayonetMapper.responseFromModel(model, service.getWeaponsByIds(model.getCompatibleWeaponsIds()));
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PatchMapping("/{id}")
    public BayonetResponse updateBayonet(@PathVariable String id, @RequestBody BayonetUpdateRequest bayonet){
        BayonetModel model = service.updateBayonet(bayonet, id);
        return BayonetMapper.responseFromModel(model, service.getWeaponsByIds(model.getCompatibleWeaponsIds()));
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @DeleteMapping("/{id}")
    public void deleteBayonet(@PathVariable String id){
        service.deleteBayonet(id);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @PostMapping("/search")
    public PageResponse<BayonetResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<BayonetModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<BayonetResponse> pageToResponse(PageResponse<BayonetModel> page){
        return PageResponse.<BayonetResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(BayonetMapper::responseFromModelSimple)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
