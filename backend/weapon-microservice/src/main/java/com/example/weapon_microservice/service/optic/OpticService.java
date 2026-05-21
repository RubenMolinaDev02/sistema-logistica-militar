package com.example.weapon_microservice.service.optic;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.optic.OpticModel;
import com.example.weapon_microservice.model.optic.dto.OpticRequest;
import com.example.weapon_microservice.model.optic.dto.OpticUpdateRequest;
import com.example.weapon_microservice.model.optic.mapper.OpticMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.repository.OpticRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OpticService {
    @Autowired
    private OpticRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private OpticQueryBuilder queryBuilder;

    public OpticModel getOpticById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Optic was not found"
                )
        );
    }

    public OpticModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Optic not found"
                )
        );
    }

    public List<OpticModel> getAllOptics() {
        return repository.findAll();
    }

    public List<OpticModel> getAllOpticsById(List<String> ids){
        return repository.findAllById(ids);
    }

    public OpticModel saveOptic(OpticRequest model){
        return repository.save(OpticMapper.modelFromRequest(model, Utils.generateId("OPT"), Utils.referenceGenerator("OPT")));
    }

    public OpticModel updateOptic(OpticUpdateRequest request, String id){
        OpticModel exists = getOpticById(id);

        itemSearchService.validateReference(request.getReference());

        if (Utils.validateString(request.getName())) exists.setName(request.getName());
        if (Utils.validateString(request.getReference())) exists.setReference(request.getReference());
        if (request.getType() != null) exists.setType(request.getType());
        if (Utils.validateInt(request.getMaxZoom())) exists.setMaxZoom(request.getMaxZoom());
        if (Utils.validateInt(request.getMinZoom())) exists.setMinZoom(request.getMinZoom());
        if (request.getMountType() != null) exists.setMountType(request.getMountType());

        return repository.save(exists);
    }

    public void deleteOptic(String id){
        OpticModel exists = getOpticById(id);

        repository.delete(exists);
    }

    public PageResponse<OpticModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
