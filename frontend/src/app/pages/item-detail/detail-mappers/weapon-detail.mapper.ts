import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

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