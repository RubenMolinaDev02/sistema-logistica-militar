import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToBayonetDetail(item: any): ItemDetailModel {

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

            label: 'Type',

            value: item.type,

            type: 'BADGE'
          },

          {
            key: 'bladeLength',

            label: 'Blade Length',

            value: item.bladeLength,

            type: 'NUMBER',

            unit: 'mm'
          }

        ]
      },

      /*
       * COMPATIBLE WEAPONS
       */
      {
        title: 'Compatible Weapons',

        fields: item.compatibleWeapons?.map((w: any) => ({

          key: w.id,

          label: w.name,

          value: `${w.reference} (${w.status})`,

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