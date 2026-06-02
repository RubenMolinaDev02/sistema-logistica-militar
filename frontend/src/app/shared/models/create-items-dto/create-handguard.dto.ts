import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_HANDGUARD_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Handguard Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Handguard Image',
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
          endpoint: '/armory/platforms'
        }

      ]
    },

    /*
     * TECHNICAL
     */
    {
      title: 'Technical',

      fields: [

        {
          key: 'mountType',
          label: 'Mount Type',
          type: 'SELECT',

          value: '',

          options: [

            'PICATINNY',
            'MLOK',
            'KEYMOD',
            'NONE'

          ]
        },

        {
          key: 'material',
          label: 'Material',
          type: 'SELECT',

          value: '',

          options: [

            'WOOD',
            'BAKELITE',
            'POLYMER',
            'ALUMINUM',
            'STEEL',
            'OTHER'

          ]
        }

      ]
    }

  ]

};