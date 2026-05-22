import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToHelmetDetail(item: any): ItemDetailModel {

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
            key: 'level',

            label: 'Protection Level',

            value: item.level,

            type: 'BADGE'
          },

          {
            key: 'material',

            label: 'Material',

            value: item.material,

            type: 'TEXT'
          }

        ]
      },

      /*
       * SPECIFICATIONS
       */
      {
        title: 'Specifications',

        fields: [

          {
            key: 'weight',

            label: 'Weight',

            value: item.weight,

            type: 'NUMBER',

            unit: 'kg'
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
      }

    ]

  };
}