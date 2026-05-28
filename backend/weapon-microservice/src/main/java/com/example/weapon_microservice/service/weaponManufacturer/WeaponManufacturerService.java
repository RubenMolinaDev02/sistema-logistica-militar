package com.example.weapon_microservice.service.weaponManufacturer;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.manufacturer.dto.ManufacturerRequest;
import com.example.weapon_microservice.model.manufacturer.WeaponManufacturerModel;
import com.example.weapon_microservice.model.manufacturer.dto.ManufacturerUpdateRequest;
import com.example.weapon_microservice.model.manufacturer.mapper.ManufacturerMapper;
import com.example.weapon_microservice.model.miscItems.MiscItemModel;
import com.example.weapon_microservice.repository.WeaponManufacturerRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WeaponManufacturerService {
    @Autowired
    private WeaponManufacturerRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private ManufacturerQueryBuilder queryBuilder;

    public WeaponManufacturerModel getWeaponManufacturerById(String id){
        return repository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Manufacturer not found"
                )

        );
    }

    public WeaponManufacturerModel getByReference(String reference){
        return repository.readByReference(reference);
    }

    public List<WeaponManufacturerModel> getAllWeaponManufacturers() {
        return repository.findAll();
    }

    public WeaponManufacturerModel saveWeaponManufacturer(ManufacturerRequest model){
        return repository.save(ManufacturerMapper.modelFromRequest(model, Utils.generateId("MAN"), Utils.referenceGenerator("MAN")));
    }

    public WeaponManufacturerModel updateWeaponManufacturer(ManufacturerUpdateRequest request, String id){
        WeaponManufacturerModel exists = getWeaponManufacturerById(id);

        itemSearchService.validateReference(request.getReference(), exists.getReference());

            if (Utils.validateString(exists.getName())) exists.setName(request.getName());

            if (Utils.validateString(request.getReference())) exists.setReference(request.getReference());

            if (Utils.validateString(request.getLogo())) exists.setLogo(request.getLogo());

            if (Utils.validateString(request.getCountry())) exists.setCountry(request.getCountry());

        return repository.save(exists);
    }

    public void deleteWeaponManufacturer(String id){
        WeaponManufacturerModel model = getWeaponManufacturerById(id);
        repository.delete(model);
    }

    public PageResponse<WeaponManufacturerModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
