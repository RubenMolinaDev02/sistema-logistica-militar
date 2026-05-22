import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToNvgDetail(item: any): ItemDetailModel {

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
            key: 'generation',

            label: 'Generation',

            value: item.generation,

            type: 'BADGE'
          },

          {
            key: 'range',

            label: 'Operational Range',

            value: item.range,

            type: 'NUMBER',

            unit: 'm'
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