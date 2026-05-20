package com.example.weapon_microservice.service.weapon;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.weapon.dto.WeaponRequest;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.manufacturer.WeaponManufacturerModel;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.dto.WeaponUpdateRequest;
import com.example.weapon_microservice.model.weapon.mapper.WeaponMapper;
import com.example.weapon_microservice.repository.WeaponRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.caliber.CaliberService;
import com.example.weapon_microservice.service.handguard.HandguardService;
import com.example.weapon_microservice.service.platform.PlatformService;
import com.example.weapon_microservice.service.stock.WeaponStockService;
import com.example.weapon_microservice.service.weaponManufacturer.WeaponManufacturerService;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Set;

@Service
public class WeaponService {
    @Autowired
    private WeaponQueryBuilder weaponQueryBuilder;
    @Autowired
    private WeaponRepository repository;
    @Autowired
    private CaliberService caliberService;
    @Autowired
    private HandguardService handguardService;
    @Autowired
    private WeaponStockService weaponStockService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private WeaponManufacturerService manufacturerService;
    @Autowired
    private ItemSearchService itemSearchService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public WeaponModel getWeaponById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Weapon not found"
                ));
    }

    public PageResponse<WeaponModel> getAllWeapons(int page, int size){
        Query query = new Query();

        query.skip((long) page * size);
        query.limit(size);

        List<WeaponModel> content =
                mongoTemplate.find(query, WeaponModel.class);

        long total =
                mongoTemplate.count(new Query(), WeaponModel.class);

        return PageResponse.<WeaponModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }

    public WeaponModel saveWeapon(WeaponRequest weapon){
        validateWeapon(weapon);

        return repository.save(WeaponMapper.modelFromRequest(
                weapon,
                Utils.generateId("WEAP"),
                Utils.referenceGenerator("WEAP")));
    }

    public WeaponModel getByReference(String reference){
        return repository.readByReference(reference)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Weapon not found"
                ));
    }

    public List<WeaponModel> findAllByIds(List<String> ids){
        return repository.findAllById(ids);
    }

    public WeaponModel updateWeapon(WeaponUpdateRequest weapon, String id){
        WeaponModel existing = getWeaponById(id);

        validateWeaponUpdate(weapon);

        if (Utils.validateString(weapon.getCaliberId())) existing.setCaliberId(weapon.getCaliberId());

        if (Utils.validateString(weapon.getHandguardId())) existing.setHandguardId(weapon.getHandguardId());

        if (Utils.validateString(weapon.getStockId())) existing.setStockId(weapon.getStockId());

        if (Utils.validateString(weapon.getCaliberId())) existing.setCaliberId(weapon.getCaliberId());

        if (Utils.validateString(weapon.getPlatformId())) existing.setPlatformId(weapon.getPlatformId());

        if (Utils.validateString(weapon.getName())) existing.setName(weapon.getName().trim());

        if (Utils.validateString(weapon.getReference())) existing.setReference(weapon.getReference());

        if (Utils.validateString(weapon.getImage())) existing.setImage(weapon.getImage().trim());

        if (weapon.getType() != null) existing.setType(weapon.getType());

        if (Utils.validateInt(weapon.getEffectiveDistance())) existing.setEffectiveDistance(weapon.getEffectiveDistance());

        if (Utils.validateDouble(weapon.getFireRate())) existing.setFireRate(weapon.getFireRate());

        if (Utils.validateDouble(weapon.getBarrelLength())) existing.setBarrelLength(weapon.getBarrelLength());

        if (weapon.getFireMode() != null) existing.setFireMode(weapon.getFireMode());

        if (weapon.getAction() != null) existing.setAction(weapon.getAction());

        if (weapon.getSightMount() != null) existing.setSightMount(weapon.getSightMount());

        if (weapon.getHasBayonetMount() != null) existing.setHasBayonetMount(weapon.getHasBayonetMount());

        if (weapon.getStockAttachmentSystem() != null) existing.setStockAttachmentSystem(weapon.getStockAttachmentSystem());

        if (weapon.getCompatibleWithSupressor() != null) existing.setCompatibleWithSupressor(weapon.getCompatibleWithSupressor());

        if (weapon.getStatus() != null) existing.setStatus(weapon.getStatus());

        return repository.save(existing);
    }

    public void deleteWeapon(String id){
        WeaponModel existing = getWeaponById(id);

        repository.delete(existing);
    }

    public PlatformModel getPlatformById(String id){
        return platformService.getPlatformById(id);
    }

    public CaliberModel getCaliberById(String id){
        return caliberService.getCaliberById(id);
    }

    public WeaponManufacturerModel getManufacturerById(String id){
        return manufacturerService.getWeaponManufacturerById(id);
    }

    public WeaponStockModel getStockById(String id){
        return weaponStockService.getWeaponStockById(id);
    }

    public HandguardModel getHandguardById(String id){
        return handguardService.getHandguardById(id);
    }

    public PageResponse<WeaponModel> search(SearchRequest request, int page, int size) {
        return weaponQueryBuilder.search(request, page, size);
    }

    public void validateWeapon(WeaponRequest weaponRequest){
        getPlatformById(weaponRequest.getPlatformId());
        if (weaponRequest.getHandguardId() != null) getHandguardById(weaponRequest.getHandguardId());
        if (weaponRequest.getStockId() != null) getStockById(weaponRequest.getStockId());
        getCaliberById(weaponRequest.getCaliberId());
        getManufacturerById(weaponRequest.getManufacturerId());
    }

    public void validateWeaponUpdate(WeaponUpdateRequest weaponRequest){
        itemSearchService.validateReference(weaponRequest.getReference());
        if (weaponRequest.getPlatformId() != null) getPlatformById(weaponRequest.getPlatformId());
        if (weaponRequest.getHandguardId() != null) getHandguardById(weaponRequest.getHandguardId());
        if (weaponRequest.getStockId() != null) getStockById(weaponRequest.getStockId());
        if (weaponRequest.getCaliberId() != null) getCaliberById(weaponRequest.getCaliberId());
        if (weaponRequest.getManufacturerId() != null) getManufacturerById(weaponRequest.getManufacturerId());
    }

}
