package com.example.weapon_microservice.service.gasMask;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.gasMask.GasMaskModel;
import com.example.weapon_microservice.model.gasMask.dto.GasMaskRequest;
import com.example.weapon_microservice.model.gasMask.dto.GasMaskUpdateRequest;
import com.example.weapon_microservice.model.gasMask.mapper.GasMaskMapper;
import com.example.weapon_microservice.model.gasMaskFilter.GasMaskFilterModel;
import com.example.weapon_microservice.repository.GasMaskRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class GasMaskService {
    @Autowired
    private GasMaskRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private GasMaskQueryBuilder queryBuilder;

    public GasMaskModel getGasMaskById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Gas Mask not found"
                ));
    }

    public GasMaskModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Gas Mask not found"
                )
        );
    }

    public List<GasMaskModel> getAllGasMasks() {
        return repository.findAll();
    }

    public GasMaskModel saveGasMask(GasMaskRequest model){
        return repository.save(GasMaskMapper.modelFromRequest(model, Utils.generateId("MSK"), Utils.referenceGenerator("MSK")));
    }
    public GasMaskModel updateGasMask(GasMaskUpdateRequest request, String id){
        GasMaskModel exists = getGasMaskById(id);

        itemSearchService.validateReference(request.getReference(), exists.getReference());
        if (Utils.validateString(request.getReference())) exists.setReference(request.getReference());

        if (Utils.validateString(request.getName())) exists.setName(request.getName());
        if (Utils.validateString(request.getImage())) exists.setImage(request.getImage());
        if (request.getStandard() != null) exists.setStandard(request.getStandard());
        if (request.getStatus() != null) exists.setStatus(request.getStatus());

        return repository.save(exists);
    }

    public void deleteGasMask(String id){
        GasMaskModel exists = getGasMaskById(id);
        repository.delete(exists);
    }

    public PageResponse<GasMaskModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
