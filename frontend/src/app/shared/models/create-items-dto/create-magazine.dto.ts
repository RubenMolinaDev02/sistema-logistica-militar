import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_MAGAZINE_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Magazine Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Magazine Image',
          type: 'IMAGE_UPLOAD',
          value: ''
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
          key: 'compatiblePlatformsIds',
          label: 'Compatible Platforms',
          type: 'SELECT_REMOTE_MULTIPLE',
          value: [],
          endpoint: 'platforms'
        },

        {
          key: 'caliberId',
          label: 'Caliber',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: 'calibers'
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
          key: 'capacity',
          label: 'Capacity',
          type: 'NUMBER',
          value: 0
        },

        {
          key: 'type',
          label: 'Magazine Type',
          type: 'SELECT',

          value: '',

          options: [

            'BOX',
            'DRUM',
            'STICK',
            'BELT',
            'STRIPPER_CLIP',
            'SPEED_LOADER',
            'OTHER'

          ]
        }

      ]
    }

  ]

};