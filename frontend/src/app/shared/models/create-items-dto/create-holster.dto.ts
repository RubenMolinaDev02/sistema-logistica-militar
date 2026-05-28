import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_HOLSTER_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Holster Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Holster Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        }

      ]
    },

    /*
     * CLASSIFICATION
     */
    {
      title: 'Classification',

      fields: [

        {
          key: 'type',
          label: 'Holster Type',
          type: 'SELECT',

          value: '',

          options: [

            'BELT',
            'MOLLE',
            'DROP_LEG',
            'CONCEALED',
            'CHEST'

          ]
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
          key: 'compatibleWeaponIds',
          label: 'Compatible Weapons',
          type: 'SELECT_REMOTE_MULTIPLE',
          value: [],
          endpoint: 'weapons'
        }

      ]
    },

    /*
     * OPTIONS
     */
    {
      title: 'Options',

      fields: [

        {
          key: 'universal',
          label: 'Universal Fit',
          type: 'BOOLEAN',
          value: false
        }

      ]
    }

  ]

};