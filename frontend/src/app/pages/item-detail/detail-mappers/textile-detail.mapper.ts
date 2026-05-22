import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToTextileDetail(item: any): ItemDetailModel {

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

            label: 'Textile Type',

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
          }

        ]
      }

    ]

  };
}