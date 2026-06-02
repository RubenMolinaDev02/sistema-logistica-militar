import { ChangeDetectorRef, Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { DynamicFormComponent } from '../../shared/components/dynamic-form-component/dynamic-form.component';
import { CREATE_WEAPON_MODEL } from '../../shared/models/create-items-dto/create-weapon.dto'; 
import { ApiInfoService } from '../../core/services/api.info.service';
import { CREATE_PLATFORM_MODEL } from '../../shared/models/create-items-dto/create-platform.dto';
import { hydrateForm } from '../../shared/models/form-hydrator';
import { UPDATE_WEAPON_MODEL } from '../../shared/models/update-items-dto/weapon-update.dto';
import { DynamicFieldComponent } from "../../shared/components/decorative-footer/decorative-footer.component";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { UPDATE_CALIBER_MODEL } from '../../shared/models/update-items-dto/caliber-update.dto';
import { UPDATE_AMMO_MODEL } from '../../shared/models/update-items-dto/ammo-update.dto';
import { UPDATE_MAGAZINE_MODEL } from '../../shared/models/update-items-dto/magazine-update.dto';
import { UPDATE_HELMET_MODEL } from '../../shared/models/update-items-dto/helmet-update.dto';
import { UPDATE_ARMOR_VEST_MODEL } from '../../shared/models/update-items-dto/armor-vest-update.dto';
import { UPDATE_NVG_MODEL } from '../../shared/models/update-items-dto/nvg-update.dto';
import { UPDATE_ARMOR_PLATE_MODEL } from '../../shared/models/update-items-dto/armor-plate-update.dto';
import { UPDATE_TEXTILE_MODEL } from '../../shared/models/update-items-dto/textile-update.dto';
import { UPDATE_OPTIC_MODEL } from '../../shared/models/update-items-dto/optic-update.dto';
import { UPDATE_HOLSTER_MODEL } from '../../shared/models/update-items-dto/holster-update.dto';
import { UPDATE_GRENADE_MODEL } from '../../shared/models/update-items-dto/grenade-update.dto';
import { UPDATE_GAS_MASK_FILTER_MODEL } from '../../shared/models/update-items-dto/gas-mask-filter-update.dto';
import { UPDATE_GAS_MASK_MODEL } from '../../shared/models/update-items-dto/gas-mask-update.dto';
import { UPDATE_BAYONET_MODEL } from '../../shared/models/update-items-dto/bayonet-update.dto';
import { UPDATE_BARREL_ATTACHMENT_MODEL } from '../../shared/models/update-items-dto/barrel-attachment-update.dto';
import { UPDATE_ATTACHMENT_MODEL } from '../../shared/models/update-items-dto/attachment-update.dto';
import { UPDATE_PLATFORM_MODEL } from '../../shared/models/update-items-dto/platform-update.dto';
import { UPDATE_STOCK_MODEL } from '../../shared/models/update-items-dto/weapon-stock-update.dto';
import { UPDATE_HANDGUARD_MODEL } from '../../shared/models/update-items-dto/handguard-update.dto';
import { UPDATE_MISC_ITEM_MODEL } from '../../shared/models/update-items-dto/misc-item-update.dto';

@Component({
  selector: 'app-item-edit',
  standalone: true,
  imports: [CommonModule, DynamicFormComponent, DynamicFieldComponent, ItemHeader, UiButtonComponent],
  templateUrl: './item-edit.component.html'
})
export class ItemEditComponent {

  model: any;

  category = "";

  id = "";

  ep = "";

  constructor(
    private api: ApiInfoService,
    private router: Router,
    private route: ActivatedRoute,
    private info: ApiInfoService,
    private cdr: ChangeDetectorRef,
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

    this.category =
      this.route.snapshot.paramMap.get('category') ?? '';

    this.id =
      this.route.snapshot.paramMap.get('id') ?? '';

    const template = this.getTemplate(this.category);

    const map: Record<string, any> = {
      weapons: `weapons`,
      ammo: `calibers`,
      ammotype: `ammo`,
      magazines: `magazines`,
      helmets: `helmet`,
      armorVests: `vests`,
      nvgs: `nvg`,
      plates: `plates`,
      textile: `textile`,
      optics: `optics`,
      holsters: `holsters`,
      grenades: `grenades`,
      gasmaskfilter: `gas-mask-filter`,
      gasmask: `gas-mask`,
      bayonets: `bayonets`,
      barrelattachments: `barrel-attachments`,
      attachments: `attachments`,
      platforms: `platforms`,
      stocks: `stocks`,
      handguards: `handguards`,
      misc: `misc`,
    };

    this.ep = map[this.category];

    this.api.getItemsById(this.id, `/armory/${this.ep}/model/`)
    .subscribe({
      next: (data) => {
        this.model = hydrateForm(template, data);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading item:', err);
        this.model = hydrateForm(template, {}); // fallback seguro
        this.cdr.detectChanges();
      }
    });
  }

  private getTemplate(category: string) {

  switch (category) {

    case 'weapons':
      return UPDATE_WEAPON_MODEL;
      
    case 'ammo':
      return UPDATE_CALIBER_MODEL;

    case 'ammotype':
      return UPDATE_AMMO_MODEL;

    case 'magazines':
      return UPDATE_MAGAZINE_MODEL;

    case 'helmets':
      return UPDATE_HELMET_MODEL;

    case 'armorVests':
      return UPDATE_ARMOR_VEST_MODEL;

    case 'nvgs':
      return UPDATE_NVG_MODEL;

    case 'plates':
      return UPDATE_ARMOR_PLATE_MODEL;

    case 'textile':
      return UPDATE_TEXTILE_MODEL;

    case 'optics':
      return UPDATE_OPTIC_MODEL;

    case 'holsters':
      return UPDATE_HOLSTER_MODEL;

    case 'grenades':
      return UPDATE_GRENADE_MODEL;

    case 'gasmaskfilter':
      return UPDATE_GAS_MASK_FILTER_MODEL;

    case 'gasmask':
      return UPDATE_GAS_MASK_MODEL;

    case 'bayonets':
      return UPDATE_BAYONET_MODEL;

    case 'barrelattachments':
      return UPDATE_BARREL_ATTACHMENT_MODEL;

    case 'attachments':
      return UPDATE_ATTACHMENT_MODEL;

    case 'platforms':
      return UPDATE_PLATFORM_MODEL;

    case 'stocks':
      return UPDATE_STOCK_MODEL;

    case 'handguards':
      return UPDATE_HANDGUARD_MODEL;

    case 'misc':
      return UPDATE_MISC_ITEM_MODEL;

    default:
      return UPDATE_WEAPON_MODEL;
  }
}

  handleCreate(payload: any) {
  this.info.editItem(payload, `/armory/${this.ep}/${this.id}`).subscribe({
    next: () => {
        this.router.navigate(['/items', this.category]);
    }
  });
}

  cancel() {
    this.router.navigate(['/items', this.category]);
  }
}