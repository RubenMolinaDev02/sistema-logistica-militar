package com.example.weapon_microservice.service.barrelAtachment;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.barrelAtachment.BarrelAtachmentModel;
import com.example.weapon_microservice.model.barrelAtachment.dto.BarrelAtachmentRequest;
import com.example.weapon_microservice.model.barrelAtachment.dto.BarrelAtachmentUpdateRequest;
import com.example.weapon_microservice.model.barrelAtachment.mapper.BarrelAtachmentMapper;
import com.example.weapon_microservice.model.bayonet.BayonetModel;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.repository.BarrelAtachmentRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.caliber.CaliberService;
import com.example.weapon_microservice.service.platform.PlatformService;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BarrelAtachmentService {
    @Autowired
    private BarrelAtachmentRepository repository;
    @Autowired
    private CaliberService caliberService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private BarrelAtachmentQueryBuilder queryBuilder;

    public BarrelAtachmentModel getBarrelAtachmentById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Barrel Attachment not found"
                )
        );
    }

    public BarrelAtachmentModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Barrel Attachment not found"
                )
        );
    }

    public List<BarrelAtachmentModel> getAllBarrelAtachments() {
        return repository.findAll();
    }

    public BarrelAtachmentModel saveBarrelAtachment(BarrelAtachmentRequest model){
        model.setCompatiblePlatforms(filterExistingPlatformIds(model.getCompatiblePlatforms()));
        getCaliberById(model.getCompatibleCaliber());
        return repository.save(BarrelAtachmentMapper.modelFromRequest(model, Utils.generateId("BATT"), Utils.referenceGenerator("BATT")));
    }

    public BarrelAtachmentModel updateBarrelAtachment(BarrelAtachmentUpdateRequest request, String id){
        BarrelAtachmentModel exists = getBarrelAtachmentById(id);

        itemSearchService.validateReference(request.getReference(), exists.getReference());
        if (Utils.validateString(request.getReference())) exists.setReference(request.getReference());

        if (Utils.validateString(request.getName())) exists.setName(request.getName());

        if (request.getType() != null) exists.setType(request.getType());

        if (request.getCompatiblePlatformsIds() != null) exists.setCompatiblePlatformsIds(filterExistingPlatformIds(request.getCompatiblePlatformsIds()));

        if (Utils.validateString(request.getCompatibleCaliber())){
            getCaliberById(request.getCompatibleCaliber());
            exists.setCompatibleCaliber(request.getCompatibleCaliber());
        }

        return repository.save(exists);
    }

    public void deleteBarrelAtachment(String id){
        BarrelAtachmentModel barrelAtachmentModel = getBarrelAtachmentById(id);
        repository.delete(barrelAtachmentModel);
    }

    private List<String> filterExistingPlatformIds(List<String> ids) {

        List<PlatformModel> platforms = getPlatformsByIds(ids);

        return platforms.stream()
                .map(PlatformModel::getId)
                .toList();
    }

    public CaliberModel getCaliberById(String id){
        return caliberService.getCaliberById(id);
    }

    public List<PlatformModel> getPlatformsByIds(List<String> ids){
        return platformService.getAllPlatformsById(ids);
    }

    public PageResponse<BarrelAtachmentModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
