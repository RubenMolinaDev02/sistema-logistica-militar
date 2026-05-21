package com.example.weapon_microservice.service.helmet;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.helmet.HelmetModel;
import com.example.weapon_microservice.model.helmet.dto.HelmetRequest;
import com.example.weapon_microservice.model.helmet.dto.HelmetUpdateRequest;
import com.example.weapon_microservice.model.helmet.mapper.HelmetMapper;
import com.example.weapon_microservice.model.holster.HolsterModel;
import com.example.weapon_microservice.repository.HelmetRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class HelmetService {
    @Autowired
    private HelmetRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private HelmetQueryBuilder queryBuilder;

    public HelmetModel getHelmetById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Helmet not found"
                )
        );
    }

    public HelmetModel getHelmetByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Helmet not found"
                )
        );
    }

    public List<HelmetModel> getAllHelmet() {
        return repository.findAll();
    }

    public HelmetModel saveHelmet(HelmetRequest helmetModel){
        return repository.save(HelmetMapper.modelFromRequest(helmetModel, Utils.generateId("HLMT"), Utils.referenceGenerator("HLMT")));
    }

    public HelmetModel updateHelmet(HelmetUpdateRequest helmet, String id) {
        HelmetModel exists = getHelmetById(id);

        itemSearchService.validateReference(helmet.getReference());
        if (Utils.validateString(helmet.getReference())) exists.setReference(helmet.getReference());

        if (Utils.validateString(helmet.getName())) exists.setName(helmet.getName());
        if (helmet.getMaterial() != null) exists.setMaterial(helmet.getMaterial());
        if (helmet.getLevel() != null) exists.setLevel(helmet.getLevel());
        if (Utils.validateDouble(helmet.getWeight())) exists.setWeight(helmet.getWeight());

        return repository.save(exists);
    }

    public void deleteHelmet(String id) {
        HelmetModel helmetModel = getHelmetById(id);
        repository.delete(helmetModel);
    }

    public PageResponse<HelmetModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
