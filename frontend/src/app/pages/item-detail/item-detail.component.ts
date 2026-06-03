import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiInfoService } from '../../core/services/api.info.service';
import { ActivatedRoute, Router } from '@angular/router';
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
import { mapToPlatformDetail } from './detail-mappers/platform-detail.mapper';
import { mapToAmmoDetail } from './detail-mappers/ammo-type.mapper';
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { mapToStockDetail } from './detail-mappers/weapon-stock-detail.mapper';
import { mapToHandguardDetail } from './detail-mappers/handguard-detail.mapper';
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { AuthService } from '../../core/services/auth.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-item-detail',
  standalone: true,
  imports: [CommonModule, UiButtonComponent, ItemHeader],
  templateUrl: './item-detail.component.html'
})
export class ItemDetailComponent {

    constructor(
        private apiInfoService: ApiInfoService,
        private route: ActivatedRoute,
        private cdr: ChangeDetectorRef,
        private router: Router,
        public authService : AuthService
    ) {}

    environment = environment;

    loading = false;

  item: any = null;

  category = '';
  id = '';

  openEdit(category: string) {
  this.router.navigate([`/items/edit/${category}/${this.id}`])
  }

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

  back(category: string){
  this.router.navigate([`/items/${category}`])
}

  loadData(): void {

  this.loading = true;

  const map: Record<string, any> = {
    weapons: () => this.apiInfoService.getItemsById(this.id, `/armory/weapons/id/`),
    ammo: () => this.apiInfoService.getItemsById(this.id, `/armory/calibers/id/`),
    ammotype: () => this.apiInfoService.getItemsById(this.id, `/armory/ammo/id/`),
    magazines: () => this.apiInfoService.getItemsById(this.id, `/armory/magazines/id/`),
    armorVests: () => this.apiInfoService.getItemsById(this.id, `/armory/vests/id/`),
    nvgs: () => this.apiInfoService.getItemsById(this.id, `/armory/nvg/id/`),
    helmets: () => this.apiInfoService.getItemsById(this.id, `/armory/helmet/id/`),
    plates: () => this.apiInfoService.getItemsById(this.id, `/armory/plates/id/`),
    textile: () => this.apiInfoService.getItemsById(this.id, `/armory/textile/id/`),
    optics: () => this.apiInfoService.getItemsById(this.id, `/armory/optics/id/`),
    holsters: () => this.apiInfoService.getItemsById(this.id, `/armory/holsters/id/`),
    grenades: () => this.apiInfoService.getItemsById(this.id, `/armory/grenades/id/`),
    gasmaskfilter: () => this.apiInfoService.getItemsById(this.id, `/armory/gas-mask-filter/id/`),
    gasmask: () => this.apiInfoService.getItemsById(this.id, `/armory/gas-mask/id/`),
    bayonets: () => this.apiInfoService.getItemsById(this.id, `/armory/bayonets/id/`),
    barrelattachments: () => this.apiInfoService.getItemsById(this.id, `/armory/barrel-attachments/id/`),
    attachments: () => this.apiInfoService.getItemsById(this.id, `/armory/attachments/id/`),
    platforms: () => this.apiInfoService.getItemsById(this.id, `/armory/platforms/id/`),
    stocks: () => this.apiInfoService.getItemsById(this.id, `/armory/stocks/id/`),
    handguards: () => this.apiInfoService.getItemsById(this.id, `/armory/handguards/id/`),
    misc: () => this.apiInfoService.getItemsById(this.id, `/armory/misc/id/`)
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
        ammotype: () => mapToAmmoDetail(res),
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
        platforms: () => mapToPlatformDetail(res),
        stocks: () => mapToStockDetail(res),
        handguards: () => mapToHandguardDetail(res),
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




