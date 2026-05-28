import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_ARMOR_VEST_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Vest Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Vest Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        }

      ]
    },

    /*
     * PROTECTION
     */
    {
      title: 'Protection',

      fields: [

        {
          key: 'softArmor',
          label: 'Soft Armor Level',
          type: 'SELECT',

          value: '',

          options: [

            'LEVEL_IIA',
            'LEVEL_II',
            'LEVEL_IIIA',
            'NONE'

          ]
        },

        {
          key: 'compatibleWithPlates',
          label: 'Plate Compatible',
          type: 'BOOLEAN',
          value: false
        }

      ]
    },

    /*
     * STATUS
     */
    {
      title: 'Status',

      fields: [

        {
          key: 'status',
          label: 'Service Status',
          type: 'SELECT',

          value: '',

          options: [

            'ACTIVE',
            'LIMITED_USE',
            'RESERVE',
            'RETIRED',
            'CAPTURED'

          ]
        }

      ]
    }

  ]

};