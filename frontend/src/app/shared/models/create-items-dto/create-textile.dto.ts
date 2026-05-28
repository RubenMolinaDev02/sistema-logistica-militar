import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_TEXTILE_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Textile Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Image',
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
          label: 'Textile Type',
          type: 'SELECT',

          value: '',

          options: [

            'UNIFORM',
            'RAIN_GEAR',
            'WINTER_GEAR',
            'BASE_LAYER',
            'BOOTS',
            'GLOVES'

          ]
        }

      ]
    }

  ]

};