package com.example.weapon_microservice.service.handguard;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.handguard.dto.HandguardRequest;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.handguard.dto.HandguardUpdateRequest;
import com.example.weapon_microservice.model.handguard.mapper.HandguardMapper;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.repository.HandguardRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.platform.PlatformService;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HandguardService {
    @Autowired
    private HandguardRepository repository;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private HandguardQueryBuilder queryBuilder;

    public HandguardModel getHandguardById(String id){
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Handguard not found"
                        )
                );
    }

    public HandguardModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Handguard not found"
                )
        );
    }

    public List<HandguardModel> getAllHandguards() {
        return repository.findAll();
    }

    public List<HandguardModel> getCompatibleHandguards(String platformId) {
        return repository.findByCompatiblePlatformsIdsContains(platformId);
    }

    public HandguardModel saveHandguard(HandguardRequest model){
        model.setCompatiblePlatformsIds(model.getCompatiblePlatformsIds());

        return repository.save(HandguardMapper.modelFromRequest(model, Utils.generateId("HG"), Utils.referenceGenerator("HG")));
    }

    public HandguardModel updateHandguard(HandguardUpdateRequest model, String id){

        HandguardModel exists = getHandguardById(id);

        itemSearchService.validateReference(model.getReference(), exists.getReference());
        if (Utils.validateString(model.getReference())) exists.setReference(model.getReference());

        if (model.getCompatiblePlatformsIds() != null) exists.setCompatiblePlatformsIds(filterExistingPlatformIds(model.getCompatiblePlatformsIds()));

        if (Utils.validateString(model.getName())) exists.setName(model.getName());

        if (model.getMaterial() != null) exists.setMaterial(model.getMaterial());

        if (model.getMountType() != null) exists.setMountType(model.getMountType());

        return repository.save(exists);
    }

    public void deleteHandguard(String id){
        HandguardModel exists = getHandguardById(id);

        repository.delete(exists);
    }

    public List<PlatformModel> getPlatformsByIds(List<String> ids){
        return platformService.getAllPlatformsById(ids);
    }

    private List<String> filterExistingPlatformIds(List<String> ids) {

        List<PlatformModel> platforms = getPlatformsByIds(ids);

        return platforms.stream()
                .map(PlatformModel::getId)
                .toList();
    }

    public PageResponse<HandguardModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
