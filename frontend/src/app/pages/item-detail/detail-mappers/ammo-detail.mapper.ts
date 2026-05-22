import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToCaliberDetail(item: any): ItemDetailModel {

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
            key: 'standard',

            label: 'Standard',

            value: item.standard,

            type: 'TEXT'
          }

        ]
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
          },

          {
            key: 'id',

            label: 'ID',

            value: item.id,

            type: 'TEXT'
          }

        ]
      },

      /*
       * COMPATIBLE AMMO
       */
      {
        title: 'Compatible Ammo',

        fields:

          item.ammo?.map((ammo: any) => ({

            key: ammo.id,

            label: ammo.name,

            value: `${ammo.reference} • ${ammo.type}`,

            type: 'TEXT'

          })) || []

      }

    ]

  };
}