import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToVestDetail(item: any): ItemDetailModel {

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
            key: 'softArmor',

            label: 'Soft Armor Level',

            value: item.softArmor,

            type: 'BADGE'
          },

          {
            key: 'compatibleWithPlates',

            label: 'Plate Compatible',

            value: item.compatibleWithPlates,

            type: 'BOOLEAN'
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