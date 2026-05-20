package com.example.weapon_microservice.service;

import com.example.weapon_microservice.model.genericItem.dto.GenericItemResponse;
import com.example.weapon_microservice.model.genericItem.enums.ItemType;
import com.example.weapon_microservice.model.genericItem.mapper.GenericItemMapper;
import com.example.weapon_microservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemSearchService {
    
    private final WeaponRepository weaponRepository;
    private final AmmoRepository ammoRepository;
    private final MagazineRepository magazineRepository;
    private final ArmorVestRepository armorVestRepository;
    private final ArmorPlateRepository armorPlateRepository;
    private final AttachmentRepository attachmentRepository;
    private final OpticRepository opticRepository;
    private final GasMaskRepository gasMaskRepository;
    private final GasMaskFilterRepository gasMaskFilterRepository;
    private final HolsterRepository holsterRepository;
    private final TextileRepository textileRepository;
    private final GrenadeRepository grenadeRepository;
    private final BayonetRepository bayonetRepository;
    private final BarrelAtachmentRepository barrelAtachmentRepository;
    private final HandguardRepository handguardRepository;
    private final HelmetRepository helmetRepository;
    private final MiscItemRepository miscItemRepository;
    private final NvgRepository nvgRepository;
    private final WeaponStockRepository weaponStockRepository;

    public GenericItemResponse findById(String id, ItemType type) {
        return switch (type) {
            case WEAPON -> weaponRepository.findById(id)
                    .map(GenericItemMapper::weaponModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case AMMO -> ammoRepository.findById(id)
                    .map(GenericItemMapper::ammoModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case MAGAZINE -> magazineRepository.findById(id)
                    .map(GenericItemMapper::magazineModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case ARMOR_VEST -> armorVestRepository.findById(id)
                    .map(GenericItemMapper::armorVestModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case ARMOR_PLATE -> armorPlateRepository.findById(id)
                    .map(GenericItemMapper::armorPlateModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case ATTACHMENT -> attachmentRepository.findById(id)
                    .map(GenericItemMapper::attachmentModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case OPTIC -> opticRepository.findById(id)
                    .map(GenericItemMapper::opticModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case GAS_MASK -> gasMaskRepository.findById(id)
                    .map(GenericItemMapper::gasMaskModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case GAS_MASK_FILTER -> gasMaskFilterRepository.findById(id)
                    .map(GenericItemMapper::gasMaskFilterModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case HOLSTER -> holsterRepository.findById(id)
                    .map(GenericItemMapper::holsterModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case TEXTILE -> textileRepository.findById(id)
                    .map(GenericItemMapper::textileModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case GRENADE -> grenadeRepository.findById(id)
                    .map(GenericItemMapper::grenadeModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case BAYONET -> bayonetRepository.findById(id)
                    .map(GenericItemMapper::bayonetModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case BARREL_ATTACHMENT -> barrelAtachmentRepository.findById(id)
                    .map(GenericItemMapper::barrelAtachmentModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case HANDGUARD -> handguardRepository.findById(id)
                    .map(GenericItemMapper::handguardModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case HELMET -> helmetRepository.findById(id)
                    .map(GenericItemMapper::helmetModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case MISC_ITEM -> miscItemRepository.findById(id)
                    .map(GenericItemMapper::miscItemModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case NVG -> nvgRepository.findById(id)
                    .map(GenericItemMapper::nvgModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            case WEAPON_STOCK -> weaponStockRepository.findById(id)
                    .map(GenericItemMapper::weaponStockModelToGenericItem)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        };
    }
    private Optional<GenericItemResponse> getByReference(String ref) {

        return weaponRepository.readByReference(ref)
                .map(GenericItemMapper::weaponModelToGenericItem)

                .or(() -> ammoRepository.readByReference(ref)
                        .map(GenericItemMapper::ammoModelToGenericItem))

                .or(() -> magazineRepository.readByReference(ref)
                        .map(GenericItemMapper::magazineModelToGenericItem))

                .or(() -> armorVestRepository.readByReference(ref)
                        .map(GenericItemMapper::armorVestModelToGenericItem))

                .or(() -> armorPlateRepository.readByReference(ref)
                        .map(GenericItemMapper::armorPlateModelToGenericItem))

                .or(() -> attachmentRepository.readByReference(ref)
                        .map(GenericItemMapper::attachmentModelToGenericItem))

                .or(() -> opticRepository.readByReference(ref)
                        .map(GenericItemMapper::opticModelToGenericItem))

                .or(() -> gasMaskRepository.readByReference(ref)
                        .map(GenericItemMapper::gasMaskModelToGenericItem))

                .or(() -> gasMaskFilterRepository.readByReference(ref)
                        .map(GenericItemMapper::gasMaskFilterModelToGenericItem))

                .or(() -> holsterRepository.readByReference(ref)
                        .map(GenericItemMapper::holsterModelToGenericItem))

                .or(() -> textileRepository.readByReference(ref)
                        .map(GenericItemMapper::textileModelToGenericItem))

                .or(() -> grenadeRepository.readByReference(ref)
                        .map(GenericItemMapper::grenadeModelToGenericItem))

                .or(() -> bayonetRepository.readByReference(ref)
                        .map(GenericItemMapper::bayonetModelToGenericItem))

                .or(() -> barrelAtachmentRepository.readByReference(ref)
                        .map(GenericItemMapper::barrelAtachmentModelToGenericItem))

                .or(() -> handguardRepository.readByReference(ref)
                        .map(GenericItemMapper::handguardModelToGenericItem))

                .or(() -> helmetRepository.readByReference(ref)
                        .map(GenericItemMapper::helmetModelToGenericItem))

                .or(() -> miscItemRepository.readByReference(ref)
                        .map(GenericItemMapper::miscItemModelToGenericItem))

                .or(() -> nvgRepository.readByReference(ref)
                        .map(GenericItemMapper::nvgModelToGenericItem))

                .or(() -> weaponStockRepository.readByReference(ref)
                        .map(GenericItemMapper::weaponStockModelToGenericItem));
    }

    public GenericItemResponse findByReference(String ref){
        return getByReference(ref).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Item not found"
        ));
    }

    public void validateReference(String ref){
        if (ref != null && getByReference(ref).isPresent())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Reference already in use"
            );
    }
}