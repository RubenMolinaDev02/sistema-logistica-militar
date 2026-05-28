import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToStockDetail(item: any): ItemDetailModel {

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
            key: 'material',

            label: 'Material',

            value: item.material,

            type: 'TEXT'
          },

          {
            key: 'attachmentMethod',

            label: 'Attachment Method',

            value: item.atachmentMethod,

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
       * COMPATIBLE PLATFORMS
       */
      {
        title: 'Compatible Platforms',

        fields:

          item.compatiblePlatforms?.map((platform: any) => ({

            key: platform.id,

            value: {
              name: platform.name,
              reference: platform.reference,
              route: `/items/platforms/${platform.id}`
            },

            type: 'LINK'

          })) || []

      }

    ]

  };
}