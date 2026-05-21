import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InformationService } from '../../core/services/api.info.service';
import { ActivatedRoute } from '@angular/router';
import { ItemDetailModel } from '../../shared/models/detail/detailModel';

@Component({
  selector: 'app-item-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './item-detail.component.html'
})
export class ItemDetailComponent {

    constructor(
        private info: InformationService,
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
    weapons: () => this.info.getWeaponById(this.id),
    /*ammo: () => this.info.getAmmo(),
    magazines: () => this.info.getMagazines(),
    helmets: () => this.info.getHelmets(),
    armorVests: () => this.info.getArmorVests(),
    nvgs: () => this.info.getNvgs(),*/
    misc: () => this.info.getMiscItemsById(this.id)
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
        /*ammo: () => this.info.getAmmo(),
        magazines: () => this.info.getMagazines(),
        helmets: () => this.info.getHelmets(),
        armorVests: () => this.info.getArmorVests(),
        nvgs: () => this.info.getNvgs(),*/
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

export function mapToWeaponDetail(item: any): ItemDetailModel {

    

  return {
    id: item.id,
    reference: item.reference,
    name: item.name,
    image: item.image,

    sections: [
      {
        title: 'General',
        fields: [
          {
            key: 'type',
            label: 'Type',
            value: item.type,
            type: 'ARRAY'
          },
          {
            key: 'status',
            label: 'Status',
            value: item.status,
            type: 'TEXT'
          },
          {
            key: 'manufacturer',
            label: 'Manufacturer',
            value: item.manufacturer?.name,
            type: 'TEXT'
          }
        ]
      },

      {
        title: 'Specifications',
        fields: [
          {
            key: 'effectiveDistance',
            label: 'Effective Distance',
            value: item.effectiveDistance,
            type: 'NUMBER',
            unit: 'm'
          },
          {
            key: 'fireRate',
            label: 'Fire Rate',
            value: item.fireRate,
            type: 'NUMBER',
            unit: 'rpm'
          },
          {
            key: 'compatibleWithSupressor',
            label: 'Suppressor Compatible',
            value: item.compatibleWithSupressor,
            type: 'BOOLEAN'
          },
          {
            key: 'hasBayonetMount',
            label: 'Bayonet Mount',
            value: item.hasBayonetMount,
            type: 'BOOLEAN'
          }
        ]
      },

      {
        title: 'Components',
        fields: [
          {
            key: 'platform',
            label: 'Platform',
            value: item.platform?.name,
            type: 'OBJECT'
          },
          {
            key: 'caliber',
            label: 'Caliber',
            value: item.caliber?.name,
            type: 'TEXT'
          },
          {
            key: 'stock',
            label: 'Stock',
            value: item.stock?.name,
            type: 'OBJECT'
          }
        ]
      }
    ]
  };
}

export function mapToMiscDetail(item: any): ItemDetailModel {

  return {
    id: item.id,
    reference: item.reference,
    name: item.name,
    image: item.image,

    sections: [

      {
        title: 'General',
        fields: [
          {
            key: 'type',
            label: 'Type',
            value: item.type,
            type: 'TEXT'
          }
        ]
      },

      {
        title: 'Identification',
        fields: [
          {
            key: 'reference',
            label: 'Reference',
            value: item.reference,
            type: 'TEXT'
          },
          {
            key: 'id',
            label: 'ID',
            value: item.id,
            type: 'TEXT'
          }
        ]
      }

    ]
  };
}
