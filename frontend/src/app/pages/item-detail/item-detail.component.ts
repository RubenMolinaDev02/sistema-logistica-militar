import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiInfoService } from '../../core/services/api.info.service';
import { ActivatedRoute } from '@angular/router';
import { mapToWeaponDetail } from './detail-mappers/weapon-detail.mapper';
import { mapToMiscDetail } from './detail-mappers/misc-item-detail.mapper';
import { mapToCaliberDetail } from './detail-mappers/ammo-detail.mapper';
import { mapToMagazineDetail } from './detail-mappers/magazine-detail.mapper';
import { mapToHelmetDetail } from './detail-mappers/helmet-detail.mapper';
import { mapToVestDetail } from './detail-mappers/armor-vest-detail.mapper';
import { mapToNvgDetail } from './detail-mappers/nvg-detail.mapper';
import { mapToPlateDetail } from './detail-mappers/plate-detail.mapper';
import { mapToTextileDetail } from './detail-mappers/textile-detail.mapper';
import { mapToOpticDetail } from './detail-mappers/optic-detail.mapper';
import { mapToHolsterDetail } from './detail-mappers/holster-detail.mapper';
import { mapToGrenadeDetail } from './detail-mappers/grenade-detail.mapper';
import { mapToGasMaskFilterDetail } from './detail-mappers/gas-mask-filter.mapper';
import { mapToGasMaskDetail } from './detail-mappers/gas-mask-detail.mapper';
import { mapToBayonetDetail } from './detail-mappers/bayonet-detail.mapper';
import { mapToBarrelAttachmentDetail } from './detail-mappers/barrel-attachment-detail.mapper';
import { mapToAttachmentDetail } from './detail-mappers/attachment-detail.mapper';

@Component({
  selector: 'app-item-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './item-detail.component.html'
})
export class ItemDetailComponent {

    constructor(
        private apiInfoService: ApiInfoService,
        private route: ActivatedRoute,
        private cdr: ChangeDetectorRef
    ) {}

    loading = false;

  item: any = null;

  category = '';
  id = '';

  ngOnInit(): void {

    this.category =
      this.route.snapshot.paramMap.get('category') ?? '';

    this.id =
      this.route.snapshot.paramMap.get('id') ?? '';

    this.loadData();
  }

  isObject(value: any): boolean {
    return value && typeof value === 'object' && !Array.isArray(value);
  }

  isPrimitive(value: any): boolean {
    return (
      typeof value === 'string' ||
      typeof value === 'number' ||
      typeof value === 'boolean'
    );
  }

  objectEntries(obj: any) {
    return Object.entries(obj || {});
  }

  formatLabel(key: string): string {
    return key
      .replace(/([A-Z])/g, ' $1')
      .replace(/_/g, ' ')
      .replace(/^./, s => s.toUpperCase());
  }

  loadData(): void {

  this.loading = true;

  const map: Record<string, any> = {
    weapons: () => this.apiInfoService.getItemsById(this.id, "weapons"),
    ammo: () => this.apiInfoService.getItemsById(this.id, "calibers"),
    magazines: () => this.apiInfoService.getItemsById(this.id, "magazines"),
    armorVests: () => this.apiInfoService.getItemsById(this.id, "vests"),
    nvgs: () => this.apiInfoService.getItemsById(this.id, "nvg"),
    helmets: () => this.apiInfoService.getItemsById(this.id, 'helmet'),
    plates: () => this.apiInfoService.getItemsById(this.id, "plates"),
    textile: () => this.apiInfoService.getItemsById(this.id, "textile"),
    optics: () => this.apiInfoService.getItemsById(this.id, "optics"),
    holsters: () => this.apiInfoService.getItemsById(this.id, "holsters"),
    grenades: () => this.apiInfoService.getItemsById(this.id, "grenades"),
    gasmaskfilter: () => this.apiInfoService.getItemsById(this.id, "gas-mask-filter"),
    gasmask: () => this.apiInfoService.getItemsById(this.id, "gas-mask"),
    bayonets: () => this.apiInfoService.getItemsById(this.id, "bayonets"),
    barrelattachments: () => this.apiInfoService.getItemsById(this.id, "barrel-attachments"),
    attachments: () => this.apiInfoService.getItemsById(this.id, "attachments"),
    misc: () => this.apiInfoService.getItemsById(this.id, 'misc')
  };

  const req = map[this.category];

  if (!req) {
    this.loading = false;
    return;
  }

  req().subscribe({
    next: (res: any) => {

        const selectMapper: Record<string, any> = {
        weapons: () => mapToWeaponDetail(res),
        ammo: () => mapToCaliberDetail(res),
        magazines: () => mapToMagazineDetail(res),
        helmets: () => mapToHelmetDetail(res),
        armorVests: () => mapToVestDetail(res),
        nvgs: () => mapToNvgDetail(res),
        plates: () => mapToPlateDetail(res),
        textile: () => mapToTextileDetail(res),
        optics: () => mapToOpticDetail(res),
        holsters: () => mapToHolsterDetail(res),
        grenades: () => mapToGrenadeDetail(res),
        gasmaskfilter: () => mapToGasMaskFilterDetail(res),
        gasmask: () => mapToGasMaskDetail(res),
        bayonets: () => mapToBayonetDetail(res),
        barrelattachments: () => mapToBarrelAttachmentDetail(res),
        attachments: () => mapToAttachmentDetail(res),
        misc: () => mapToMiscDetail(res)
    };

      this.item = selectMapper[this.category]();
      console.log(this.item);
      this.loading = false;
      this.cdr.detectChanges();
    },
    error: () => {
      this.loading = false;
      this.cdr.detectChanges();
    }
  });
}



}




