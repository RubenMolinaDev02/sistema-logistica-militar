import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToAttachmentDetail(item: any): ItemDetailModel {

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
            key: 'mountType',

            label: 'Mount Type',

            value: item.mountType,

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
          }

        ]
      }

    ]

  };
}