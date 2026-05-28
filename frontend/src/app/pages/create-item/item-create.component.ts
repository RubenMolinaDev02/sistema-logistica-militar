import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { DynamicFormComponent } from '../../shared/components/dynamic-form-component/dynamic-form.component';
import { CREATE_WEAPON_MODEL } from '../../shared/models/create-items-dto/create-weapon.dto'; 
import { ApiInfoService } from '../../core/services/api.info.service';
import { CREATE_PLATFORM_MODEL } from '../../shared/models/create-items-dto/create-platform.dto';
import { hydrateForm } from '../../shared/models/form-hydrator';
import { CREATE_CALIBER_MODEL } from '../../shared/models/create-items-dto/create-caliber.dto';
import { CREATE_AMMO_MODEL } from '../../shared/models/create-items-dto/create-ammo.dto';
import { CREATE_MAGAZINE_MODEL } from '../../shared/models/create-items-dto/create-magazine.dto';
import { CREATE_HELMET_MODEL } from '../../shared/models/create-items-dto/create-helmet.dto';
import { CREATE_ARMOR_VEST_MODEL } from '../../shared/models/create-items-dto/create-armor-vest.dto';
import { CREATE_NVG_MODEL } from '../../shared/models/create-items-dto/create-nvg.dto';
import { CREATE_ARMOR_PLATE_MODEL } from '../../shared/models/create-items-dto/create-armor-plate.dto';
import { CREATE_TEXTILE_MODEL } from '../../shared/models/create-items-dto/create-textile.dto';
import { CREATE_OPTIC_MODEL } from '../../shared/models/create-items-dto/create-optic.dto';
import { CREATE_HOLSTER_MODEL } from '../../shared/models/create-items-dto/create-holster.dto';
import { CREATE_GRENADE_MODEL } from '../../shared/models/create-items-dto/create-grenade.dto';
import { CREATE_GAS_MASK_FILTER_MODEL } from '../../shared/models/create-items-dto/create-gas-mask-filter.dto';
import { CREATE_GAS_MASK_MODEL } from '../../shared/models/create-items-dto/create-gas-mask.dto';
import { CREATE_BAYONET_MODEL } from '../../shared/models/create-items-dto/create-bayonet.dto';
import { CREATE_BARREL_ATTACHMENT_MODEL } from '../../shared/models/create-items-dto/create-barrel-attachment.dto';
import { CREATE_ATTACHMENT_MODEL } from '../../shared/models/create-items-dto/create-attachment.dto';
import { CREATE_STOCK_MODEL } from '../../shared/models/create-items-dto/create-weapon-stock.dto';
import { CREATE_HANDGUARD_MODEL } from '../../shared/models/create-items-dto/create-handguard.dto';
import { CREATE_MISC_ITEM_MODEL } from '../../shared/models/create-items-dto/create-misc-item.dto';
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { DynamicFieldComponent } from "../../shared/components/decorative-footer/decorative-footer.component";

@Component({
  selector: 'app-item-create',
  styles: [`:host { display: flex; flex-direction: column; height: 100dvh; overflow: hidden; }`],
  standalone: true,
  imports: [CommonModule, DynamicFormComponent, ItemHeader, UiButtonComponent, DynamicFieldComponent],
  templateUrl: './item-create.component.html'
})
export class ItemCreateComponent {

  model: any;

  category = "";

  constructor(
    private api: ApiInfoService,
    private router: Router,
    private route: ActivatedRoute,
    private info: ApiInfoService
    ) { }


    submitForm() {
  const values: Record<string, any> = {};

  for (const section of this.model.sections) {
    for (const field of section.fields) {
      values[field.key] = field.value;
    }
  }

  this.handleCreate(values);
}

ngOnInit(): void {

    const map: any = {
        weapons: () => hydrateForm(CREATE_WEAPON_MODEL, {}),
        ammo: () => hydrateForm(CREATE_CALIBER_MODEL, {}),
        ammotype: () => hydrateForm(CREATE_AMMO_MODEL, {}),
        magazines: () => hydrateForm(CREATE_MAGAZINE_MODEL, {}),
        helmets: () => hydrateForm(CREATE_HELMET_MODEL, {}),
        armorVests: () => hydrateForm(CREATE_ARMOR_VEST_MODEL, {}),
        nvgs: () => hydrateForm(CREATE_NVG_MODEL, {}),
        plates: () => hydrateForm(CREATE_ARMOR_PLATE_MODEL, {}),
        textile: () => hydrateForm(CREATE_TEXTILE_MODEL, {}),
        optics: () => hydrateForm(CREATE_OPTIC_MODEL, {}),
        holsters: () => hydrateForm(CREATE_HOLSTER_MODEL, {}),
        grenades: () => hydrateForm(CREATE_GRENADE_MODEL, {}),
        gasmaskfilter: () => hydrateForm(CREATE_GAS_MASK_FILTER_MODEL, {}),
        gasmask: () => hydrateForm(CREATE_GAS_MASK_MODEL, {}),
        bayonets: () => hydrateForm(CREATE_BAYONET_MODEL, {}),
        barrelattachments: () => hydrateForm(CREATE_BARREL_ATTACHMENT_MODEL, {}),
        attachments: () => hydrateForm(CREATE_ATTACHMENT_MODEL, {}),
        platforms: () => hydrateForm(CREATE_PLATFORM_MODEL, {}),
        stocks: () => hydrateForm(CREATE_STOCK_MODEL, {}),
        handguards: () => hydrateForm(CREATE_HANDGUARD_MODEL, {}),
        misc: () => hydrateForm(CREATE_MISC_ITEM_MODEL, {}),
    };
    this.category =
      this.route.snapshot.paramMap.get('category') ?? '';

    this.model = map[this.category]();
  }

  handleCreate(payload: any) {
  console.log('CREATE PAYLOAD:', payload);

  const map: Record<string, any> = {
    weapons: (data: any) => this.info.createItem(data, "weapons"),
    ammo: (data: any) => this.info.createItem(data, "calibers"),
    ammotype: (data: any) => this.info.createItem(data, "ammo"),
    magazines: (data: any) => this.info.createItem(data, "magazines"),
    helmets: (data: any) => this.info.createItem(data, "helmet"),
    armorVests: (data: any) => this.info.createItem(data, "vests"),
    nvgs: (data: any) => this.info.createItem(data, "nvg"),
    plates: (data: any) => this.info.createItem(data, "plates"),
    textile: (data: any) => this.info.createItem(data, "textile"),
    optics: (data: any) => this.info.createItem(data, "optics"),
    holsters: (data: any) => this.info.createItem(data, "holsters"),
    grenades: (data: any) => this.info.createItem(data, "grenades"),
    gasmaskfilter: (data: any) => this.info.createItem(data, "gas-mask-filter"),
    gasmask: (data: any) => this.info.createItem(data, "gas-mask"),
    bayonets: (data: any) => this.info.createItem(data, "bayonets"),
    barrelattachments: (data: any) => this.info.createItem(data, "barrel-attachments"),
    attachments: (data: any) => this.info.createItem(data, "attachments"),
    platforms: (data: any) => this.info.createItem(data, "platforms"),
    stocks: (data: any) => this.info.createItem(data, "stocks"),
    handguards: (data: any) => this.info.createItem(data, "handguards"),
    misc: (data: any) => this.info.createItem(data, "misc"),
  };

  const req = map[this.category];

  if (!req) return;

  req(payload).subscribe({
    next: () => {
        this.router.navigate(['/items', this.category]);
    }
  });
}

  cancel() {
    this.router.navigate(['/items', this.category]);
  }
}