import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToAmmoDetail(item: any): ItemDetailModel {

  return {

    id: item.id,

    reference: item.reference,

    name: item.name,

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
       * CALIBER
       */
      {
        title: 'Caliber',

        fields: [

          {
            key: 'caliber',

            label: '',

            type: 'LINK',

            value: {

              route: `/items/ammo/${item.caliber.id}`,

              name: item.caliber.name,

              reference: item.caliber.reference

            }

          }

        ]
      }

    ]

  };

}