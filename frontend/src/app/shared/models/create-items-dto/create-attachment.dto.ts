import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_ATTACHMENT_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Attachment Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Attachment Image',
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
          label: 'Attachment Type',
          type: 'SELECT',

          value: '',

          options: [

            'UNDERBARREL_WEAPON',
            'OPTIC',
            'SIGHT',
            'LIGHT',
            'LASER',
            'FOREGRIP',
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
    }

  ]

};