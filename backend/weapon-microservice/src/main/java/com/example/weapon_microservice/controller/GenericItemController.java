package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.genericItem.dto.GenericItemResponse;
import com.example.weapon_microservice.model.genericItem.enums.ItemType;
import com.example.weapon_microservice.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/items")
public class GenericItemController {
    @Autowired
    private ItemSearchService itemService;

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/id/{id}")
    public GenericItemResponse getById(
            @PathVariable String id,
            @RequestParam ItemType type) {
        return itemService.findById(id, type);
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/ref/{ref}")
    public GenericItemResponse getByReference(
            @PathVariable String ref
    ){
        return itemService.findByReference(ref);
    }


}
