package com.example.weapon_microservice.service.magazine;

import com.example.weapon_microservice.model.magazine.dto.MagazineRequest;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.magazine.MagazineModel;
import com.example.weapon_microservice.model.magazine.dto.MagazineUpdateRequest;
import com.example.weapon_microservice.model.magazine.mapper.MagazineMapper;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.repository.MagazineRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.caliber.CaliberService;
import com.example.weapon_microservice.service.platform.PlatformService;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MagazineService {
    @Autowired
    private MagazineRepository repository;
    @Autowired
    private CaliberService caliberService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private ItemSearchService itemSearchService;

    public MagazineModel getMagazineById(String id){
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Magazine not found"
                        )
                );
    }

    public MagazineModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Magazine not found"
                )
        );
    }

    public List<MagazineModel> getAllMagazines() {
        return repository.findAll();
    }

    public MagazineModel saveMagazine(MagazineRequest model){
        filterExistingPlatformIds(model.getCompatiblePlatformsIds());

        caliberService.getCaliberById(model.getCaliberId());

        return repository.save(MagazineMapper.modelFromRequest(model, Utils.generateId("MAG"), Utils.referenceGenerator("MAG")));
    }

    public CaliberModel getCaliberById(String id){
        return caliberService.getCaliberById(id);
    }

    public List<PlatformModel> getPlatformsByIds(List<String> ids){
        return platformService.getAllPlatformsById(ids);
    }

    public MagazineModel updateMagazine(String id, MagazineUpdateRequest model) {

        MagazineModel existing = getMagazineById(id);

        itemSearchService.validateReference(model.getReference());
        if (Utils.validateString(model.getReference())) existing.setReference(model.getReference());

        if (existing.getCompatiblePlatformsIds() != null) existing.setCompatiblePlatformsIds(filterExistingPlatformIds(model.getCompatiblePlatformsIds()));

        if (Utils.validateString(model.getCaliberId()))getCaliberById(model.getCaliberId());
        existing.setCaliberId(model.getCaliberId());

        if (Utils.validateString(model.getName())) existing.setName(model.getName().trim());

        if (Utils.validateInt(model.getCapacity())) existing.setCapacity(model.getCapacity());

        if (model.getType() != null) existing.setType(model.getType());

        return repository.save(existing);
    }
    public void deleteMagazine(String id){
        MagazineModel model = getMagazineById(id);
        repository.delete(model);
    }

    private List<String> filterExistingPlatformIds(List<String> ids) {

        List<PlatformModel> platforms = getPlatformsByIds(ids);

        return platforms.stream()
                .map(PlatformModel::getId)
                .toList();
    }

}
