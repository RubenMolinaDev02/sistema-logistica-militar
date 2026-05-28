package com.example.weapon_microservice.service.gasMaskFilter;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.gasMaskFilter.GasMaskFilterModel;
import com.example.weapon_microservice.model.gasMaskFilter.dto.GasMaskFilterRequest;
import com.example.weapon_microservice.model.gasMaskFilter.dto.GasMaskFilterUpdateRequest;
import com.example.weapon_microservice.model.gasMaskFilter.mapper.GasMaskFilterMapper;
import com.example.weapon_microservice.model.grenade.GrenadeModel;
import com.example.weapon_microservice.repository.GasMaskFilterRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class GasMaskFilterService {
    @Autowired
    private GasMaskFilterRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private GasMaskFilterQueryBuilder queryBuilder;

    public GasMaskFilterModel getGasMaskFilterById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Gas Mask Filter not found"
                ));
    }

    public GasMaskFilterModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Gas Mask Filter not found"
                )
        );
    }

    public List<GasMaskFilterModel> getAllGasMaskFilters() {
        return repository.findAll();
    }

    public GasMaskFilterModel saveGasMaskFilter(GasMaskFilterRequest model){
        return repository.save(GasMaskFilterMapper.modelFromRequest(model, Utils.generateId("FLTR"), Utils.generateId("FLTR")));
    }
    public GasMaskFilterModel updateGasMaskFilter(GasMaskFilterUpdateRequest request, String id){
        GasMaskFilterModel exists = getGasMaskFilterById(id);

        itemSearchService.validateReference(request.getReference(), exists.getReference());
        if (Utils.validateString(request.getReference())) exists.setReference(request.getReference());

        if (Utils.validateString(request.getName())) exists.setName(request.getName());
        if (request.getStandard() != null) exists.setStandard(request.getStandard());
        if (Utils.validateInt(request.getUseHours())) exists.setUseHours(request.getUseHours());
        if (Utils.validateString(request.getImage())) exists.setImage(request.getImage());

        return repository.save(exists);
    }

    public void deleteGasMaskFilter(String id){
        GasMaskFilterModel exists = getGasMaskFilterById(id);
        repository.delete(exists);
    }

    public PageResponse<GasMaskFilterModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
