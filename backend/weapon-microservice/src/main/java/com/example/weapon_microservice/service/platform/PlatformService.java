package com.example.weapon_microservice.service.platform;

import com.example.weapon_microservice.model.platform.dto.PlatformRequest;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.model.platform.dto.PlatformUpdateRequest;
import com.example.weapon_microservice.model.platform.mapper.PlatformMapper;
import com.example.weapon_microservice.repository.PlatformRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlatformService {
    @Autowired
    private PlatformRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;

    public PlatformModel getPlatformById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Platform was not found"
                )
        );
    }

    public PlatformModel getByReference(String reference){
        return repository.readByReference(reference);
    }

    public List<PlatformModel> getAllPlatforms() {
        return repository.findAll();
    }

    public List<PlatformModel> getAllPlatformsById(List<String> ids){
        return repository.findAllById(ids);
    }

    public PlatformModel savePlatform(PlatformRequest model){
        return repository.save(PlatformMapper.modelFromRequest(model, Utils.generateId("PLAT"), Utils.referenceGenerator("PLAT")));
    }

    public PlatformModel updatePlatform(PlatformUpdateRequest request, String id){
        PlatformModel exists = getPlatformById(id);

        itemSearchService.validateReference(request.getReference());

        exists.setName(request.getName());

        exists.setDescription(request.getDescription());

        return repository.save(exists);
    }

    public void deletePlatform(String id){
        PlatformModel exists = getPlatformById(id);

        repository.delete(exists);
    }
}
