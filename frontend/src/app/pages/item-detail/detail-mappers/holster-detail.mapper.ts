import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToHolsterDetail(item: any): ItemDetailModel {

  return {

    id: item.id,

    reference: item.reference,

    name: item.name,

    image: item.image,

    sections: [

      /*
       * GENERAL
       */
      {
        title: 'General',

        fields: [

          {
            key: 'type',

            label: 'Holster Type',

            value: item.type,

            type: 'BADGE'
          },

          {
            key: 'universal',

            label: 'Universal Compatibility',

            value: item.universal,

            type: 'BOOLEAN'
          }

        ]
      },

      /*
       * COMPATIBLE WEAPONS
       */
      {
        title: 'Compatible Weapons',

        fields:

          item.compatibleWeapons?.map((weapon: any) => ({

            key: weapon.id,

            label: weapon.name,

            value: weapon.reference,

            type: 'TEXT'

          })) || []

      },

      /*
       * IDENTIFICATION
       */
      {
        title: 'Identification',

        fields: [

          {
            key: 'reference',

            label: 'Reference',

            value: item.reference,

            type: 'TEXT'
          }

        ]
      }

    ]

  };
}