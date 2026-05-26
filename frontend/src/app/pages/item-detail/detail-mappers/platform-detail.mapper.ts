import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToPlatformDetail(item: any): ItemDetailModel {

  return {
    id: item?.id ?? '',
    reference: item?.reference ?? '',
    name: item?.name ?? '',

    sections: [

      // GENERAL
      {
        title: 'General',
        fields: [
          {
            key: 'description',
            label: 'Description',
            value: item?.description ?? 'N/A',
            type: 'TEXT'
          },
          {
            key: 'weaponsCount',
            label: 'Weapons Count',
            value: item?.weapons?.length ?? 0,
            type: 'NUMBER'
          }
        ]
      },

      // WEAPONS (LINK LIST CORRECTO)
      {
        title: 'Weapons',
        fields: (item?.weapons ?? []).map((weapon: any) => ({
            key: weapon.id,
            label: '',
            type: 'LINK',
            value: {
            id: weapon.id,
            name: weapon.name,
            reference: weapon.reference,
            route: `/items/weapons/${weapon.id}`
            }
        }))
    }

    ]
  };
}