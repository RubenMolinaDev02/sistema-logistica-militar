package com.example.weapon_microservice.service.caliber;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.caliber.dto.CaliberRequest;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.dto.CaliberUpdateRequest;
import com.example.weapon_microservice.model.caliber.mapper.CaliberMapper;
import com.example.weapon_microservice.model.gasMask.GasMaskModel;
import com.example.weapon_microservice.repository.CaliberRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CaliberService {
    @Autowired
    private CaliberRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private CaliberQueryBuilder queryBuilder;

    public CaliberModel getCaliberById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Caliber not found"
                ));
    }

    public CaliberModel getByReference(String reference){
        return repository.readByReference(reference);
    }

    public List<CaliberModel> getAllCalibers() {
        return repository.findAll();
    }

    public CaliberModel saveCaliber(CaliberRequest model){
        return repository.save(CaliberMapper.modelFromRequest(model, Utils.generateId("CAL"), Utils.referenceGenerator("CAL")));
    }
    public CaliberModel updateCaliber(CaliberUpdateRequest request, String id){
        CaliberModel exists = getCaliberById(id);

        itemSearchService.validateReference(request.getReference(), exists.getReference());
        if (Utils.validateString(request.getReference())) exists.setReference(request.getReference());

        if (Utils.validateString(request.getName())) exists.setName(request.getName());
        if (request.getType() != null) exists.setType(request.getType());
        if (request.getStandard() != null) exists.setStandard(request.getStandard());

        return repository.save(exists);
    }

    public void deleteCaliber(String id){
        CaliberModel exists = getCaliberById(id);
        repository.delete(exists);
    }

    public PageResponse<CaliberModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
