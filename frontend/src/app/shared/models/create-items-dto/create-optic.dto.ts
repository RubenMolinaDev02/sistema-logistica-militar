import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_OPTIC_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Optic Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Optic Image',
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
          label: 'Optic Type',
          type: 'SELECT',

          value: '',

          options: [

            'RED_DOT',
            'RED_DOT_PISTOL',
            'HOLOGRAPHIC',
            'VARIABLE_SCOPE',
            'FIXED_SCOPE',
            'NIGHT_VISION',
            'THERMAL',
            'IRON_SIGHTS',
            'OTHER'

          ]
        },

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
          key: 'minZoom',
          label: 'Min Zoom',
          type: 'NUMBER',
          value: 0
        },

        {
          key: 'maxZoom',
          label: 'Max Zoom',
          type: 'NUMBER',
          value: 0
        }

      ]
    }

  ]

};