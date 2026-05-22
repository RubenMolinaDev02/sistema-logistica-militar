import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToBarrelAttachmentDetail(item: any): ItemDetailModel {

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

            label: 'Attachment Type',

            value: item.type,

            type: 'BADGE'
          },

          {
            key: 'supressor',

            label: 'Suppressor Compatible',

            value: item.supressor,

            type: 'BOOLEAN'
          }

        ]
      },

      /*
       * COMPATIBILITY
       */
      {
        title: 'Compatibility',

        fields: [

          {
            key: 'compatibleCaliber',

            label: 'Compatible Caliber',

            value: item.compatibleCaliber?.name,

            type: 'TEXT'
          },

          ...(item.compatiblePlatforms?.map((p: any) => ({

            key: p.id,

            label: p.name,

            value: p.reference,

            type: 'TEXT'

          })) || [])

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