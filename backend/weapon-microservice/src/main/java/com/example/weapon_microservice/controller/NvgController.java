package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.nvg.NvgModel;
import com.example.weapon_microservice.model.nvg.dto.NvgRequest;
import com.example.weapon_microservice.model.nvg.dto.NvgResponse;
import com.example.weapon_microservice.model.nvg.dto.NvgUpdateRequest;
import com.example.weapon_microservice.model.nvg.mapper.NvgMapper;
import com.example.weapon_microservice.model.optic.OpticModel;
import com.example.weapon_microservice.model.optic.dto.OpticResponse;
import com.example.weapon_microservice.model.optic.mapper.OpticMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.service.SearchRequest;
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

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping
    public List<NvgResponse> getAllNvg(){
        return NvgMapper.responseFromModelList(service.getAllNvg());
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PostMapping
    public NvgResponse createNvg(@Valid @RequestBody NvgRequest nvg){
        NvgModel model = service.saveNvg(nvg);
        return NvgMapper.responseFromModel(model);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/model/{id}")
    public NvgModel getNvgByIdModel(@PathVariable String id){
        return service.getNvgById(id);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/reference/{reference}")
    public NvgResponse getNvgByReference(@PathVariable String reference){
        NvgModel nvg = service.getByReference(reference);
        return NvgMapper.responseFromModel(nvg);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/id/{id}")
    public NvgResponse getNvgById(@PathVariable String id){
        NvgModel nvg = service.getNvgById(id);
        return NvgMapper.responseFromModel(nvg);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PatchMapping("/{id}")
    public NvgResponse updateNvg(@PathVariable String id, @RequestBody NvgUpdateRequest Nvg){
        NvgModel nvg = service.updateNvg(Nvg, id);
        return NvgMapper.responseFromModel(nvg);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @DeleteMapping("/{id}")
    public void deleteNvg(@PathVariable String id){
        service.deleteNvg(id);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @PostMapping("/search")
    public PageResponse<NvgResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<NvgModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<NvgResponse> pageToResponse(PageResponse<NvgModel> page){
        return PageResponse.<NvgResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(NvgMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
