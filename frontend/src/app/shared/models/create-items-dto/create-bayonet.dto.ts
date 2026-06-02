import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_BAYONET_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Bayonet Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Bayonet Image',
          type: 'IMAGE_UPLOAD',
          value: ''
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
          key: 'bladeLength',
          label: 'Blade Length',
          type: 'NUMBER',
          value: 0,
          unit: 'cm'
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
          label: 'Bayonet Type',
          type: 'SELECT',

          value: '',

          options: [

            'KNIFE',
            'SPIKE',
            'SWORD',
            'MULTI_TOOL',
            'CEREMONIAL',
            'OTHER'

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
          key: 'compatibleWeaponsIds',
          label: 'Compatible Weapons',
          type: 'SELECT_REMOTE_MULTIPLE',
          value: [],
          endpoint: '/armory/weapons'
        }

      ]
    }

  ]

};