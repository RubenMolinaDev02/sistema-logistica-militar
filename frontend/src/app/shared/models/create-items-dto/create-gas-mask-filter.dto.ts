import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_GAS_MASK_FILTER_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Filter Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Filter Image',
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
          key: 'useHours',
          label: 'Use Hours',
          type: 'NUMBER',
          value: 0
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
    }

  ]

};