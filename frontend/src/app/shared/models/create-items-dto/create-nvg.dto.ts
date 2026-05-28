import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_NVG_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'NVG Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'NVG Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        }

      ]
    },

    /*
     * PERFORMANCE
     */
    {
      title: 'Performance',

      fields: [

        {
          key: 'generation',
          label: 'Generation',
          type: 'SELECT',

          value: '',

          options: [

            'GEN1',
            'GEN2',
            'GEN3',
            'GEN3_PLUS'

          ]
        },

        {
          key: 'range',
          label: 'Range (m)',
          type: 'NUMBER',
          value: 0
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