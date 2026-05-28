import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_GAS_MASK_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Gas Mask Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Gas Mask Image',
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
          key: 'standard',
          label: 'Gas Mask Standard',
          type: 'SELECT',

          value: '',

          options: [

            'STANAG',
            'GOST'

          ]
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