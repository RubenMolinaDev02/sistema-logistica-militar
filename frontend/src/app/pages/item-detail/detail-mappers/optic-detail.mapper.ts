import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToOpticDetail(item: any): ItemDetailModel {

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

            label: 'Optic Type',

            value: item.type,

            type: 'BADGE'
          },

          {
            key: 'mountType',

            label: 'Mount Type',

            value: item.mountType,

            type: 'TEXT'
          }

        ]
      },

      /*
       * OPTICAL DATA
       */
      {
        title: 'Optical Specifications',

        fields: [

          {
            key: 'minZoom',

            label: 'Minimum Zoom',

            value: item.minZoom,

            type: 'NUMBER',

            unit: 'x'
          },

          {
            key: 'maxZoom',

            label: 'Maximum Zoom',

            value: item.maxZoom,

            type: 'NUMBER',

            unit: 'x'
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