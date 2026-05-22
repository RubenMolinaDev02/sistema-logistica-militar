import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToGrenadeDetail(item: any): ItemDetailModel {

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

            label: 'Grenade Type',

            value: item.type,

            type: 'BADGE'
          },

          {
            key: 'lethal',

            label: 'Lethal',

            value: item.lethal,

            type: 'BOOLEAN'
          }

        ]
      },

      /*
       * FUZE & ARMING
       */
      {
        title: 'Fuze & Arming',

        fields: [

          {
            key: 'armingMethod',

            label: 'Arming Method',

            value: item.armingMethod,

            type: 'TEXT'
          },

          {
            key: 'armingDistance',

            label: 'Arming Distance',

            value: item.armingDistance,

            type: 'NUMBER',

            unit: 'm'
          },

          {
            key: 'fuzeDelay',

            label: 'Fuze Delay',

            value: item.fuzeDelay,

            type: 'NUMBER',

            unit: 's'
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
          }

        ]
      }

    ]

  };
}