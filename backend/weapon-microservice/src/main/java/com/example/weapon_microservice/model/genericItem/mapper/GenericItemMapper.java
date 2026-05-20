package com.example.weapon_microservice.model.genericItem.mapper;

import com.example.weapon_microservice.model.ammo.AmmoModel;
import com.example.weapon_microservice.model.armorPlate.ArmorPlateModel;
import com.example.weapon_microservice.model.armorVest.ArmorVestModel;
import com.example.weapon_microservice.model.attachment.AttachmentModel;
import com.example.weapon_microservice.model.barrelAtachment.BarrelAtachmentModel;
import com.example.weapon_microservice.model.bayonet.BayonetModel;
import com.example.weapon_microservice.model.gasMask.GasMaskModel;
import com.example.weapon_microservice.model.gasMaskFilter.GasMaskFilterModel;
import com.example.weapon_microservice.model.genericItem.dto.GenericItemResponse;
import com.example.weapon_microservice.model.genericItem.enums.ItemType;
import com.example.weapon_microservice.model.grenade.GrenadeModel;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.helmet.HelmetModel;
import com.example.weapon_microservice.model.holster.HolsterModel;
import com.example.weapon_microservice.model.magazine.MagazineModel;
import com.example.weapon_microservice.model.miscItems.MiscItemModel;
import com.example.weapon_microservice.model.nvg.NvgModel;
import com.example.weapon_microservice.model.optic.OpticModel;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.model.weapon.WeaponModel;

public class GenericItemMapper {
    public static GenericItemResponse weaponModelToGenericItem(WeaponModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.WEAPON)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse ammoModelToGenericItem(AmmoModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.AMMO)
                .reference(model.getReference())
                .build();
    }

    public static GenericItemResponse armorPlateModelToGenericItem(ArmorPlateModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.ARMOR_PLATE)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse armorVestModelToGenericItem(ArmorVestModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.ARMOR_VEST)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse attachmentModelToGenericItem(AttachmentModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.ATTACHMENT)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse barrelAtachmentModelToGenericItem(BarrelAtachmentModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.BARREL_ATTACHMENT)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse bayonetModelToGenericItem(BayonetModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.BAYONET)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse gasMaskModelToGenericItem(GasMaskModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.GAS_MASK)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse gasMaskFilterModelToGenericItem(GasMaskFilterModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.GAS_MASK_FILTER)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse grenadeModelToGenericItem(GrenadeModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.GRENADE)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse handguardModelToGenericItem(HandguardModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.HANDGUARD)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse helmetModelToGenericItem(HelmetModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.HELMET)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse holsterModelToGenericItem(HolsterModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.HOLSTER)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse magazineModelToGenericItem(MagazineModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.MAGAZINE)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse miscItemModelToGenericItem(MiscItemModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.MISC_ITEM)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse nvgModelToGenericItem(NvgModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.NVG)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse opticModelToGenericItem(OpticModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.OPTIC)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse weaponStockModelToGenericItem(WeaponStockModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.WEAPON_STOCK)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

    public static GenericItemResponse textileModelToGenericItem(TextileModel model){
        return GenericItemResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .itemType(ItemType.TEXTILE)
                .reference(model.getReference())
                .image(model.getImage())
                .build();
    }

}
