package com.example.weapon_microservice.service.grenade;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.grenade.GrenadeModel;
import com.example.weapon_microservice.model.grenade.dto.GrenadeRequest;
import com.example.weapon_microservice.model.grenade.dto.GrenadeUpdateRequest;
import com.example.weapon_microservice.model.grenade.mapper.GrenadeMapper;
import com.example.weapon_microservice.model.helmet.HelmetModel;
import com.example.weapon_microservice.repository.GrenadeRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class GrenadeService {

    @Autowired
    private GrenadeRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private GrenadeQueryBuilder queryBuilder;

    public GrenadeModel getGrenadeById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Grenade not found"
                ));
    }

    public GrenadeModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Grenade not found"
                )
        );
    }

    public List<GrenadeModel> getAllGrenades() {
        return repository.findAll();
    }

    public GrenadeModel saveGrenade(GrenadeRequest model){
        return repository.save(GrenadeMapper.modelFromRequest(model, Utils.generateId("GRND"), Utils.referenceGenerator("GRND")));
    }
    public GrenadeModel updateGrenade(GrenadeUpdateRequest request, String id){
        GrenadeModel exists = getGrenadeById(id);

        itemSearchService.validateReference(request.getReference(), exists.getReference());
        if (Utils.validateString(request.getReference())) exists.setReference(request.getReference());

        if (Utils.validateString(request.getName())) exists.setName(request.getName());
        if (request.getType() != null) exists.setType(request.getType());
        if (request.getLethal() != null) exists.setLethal(request.getLethal());
        if (Utils.validateString(request.getImage())) exists.setImage(request.getImage());
        if (Utils.validateDouble(request.getArmingDistance())) exists.setArmingDistance(request.getArmingDistance());
        if (Utils.validateDouble(request.getFuzeDelay())) exists.setFuzeDelay(request.getFuzeDelay());
        if (request.getArmingMethod() != null) exists.setArmingMethod(request.getArmingMethod());

        return repository.save(exists);
    }

    public void deleteGrenade(String id){
        GrenadeModel exists = getGrenadeById(id);
        repository.delete(exists);
    }

    public PageResponse<GrenadeModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
