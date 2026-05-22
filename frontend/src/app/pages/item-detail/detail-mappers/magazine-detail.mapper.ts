import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToMagazineDetail(item: any): ItemDetailModel {

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

            label: 'Magazine Type',

            value: item.type,

            type: 'BADGE'
          },

          {
            key: 'capacity',

            label: 'Capacity',

            value: item.capacity,

            type: 'NUMBER',

            unit: 'rounds'
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
            key: 'caliberName',

            label: 'Name',

            value: item.caliber?.name,

            type: 'TEXT'
          },

          {
            key: 'caliberType',

            label: 'Type',

            value: item.caliber?.type,

            type: 'TEXT'
          },

          {
            key: 'caliberStandard',

            label: 'Standard',

            value: item.caliber?.standard,

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

            label: platform.name,

            value: platform.reference,

            type: 'TEXT'

          })) || []

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