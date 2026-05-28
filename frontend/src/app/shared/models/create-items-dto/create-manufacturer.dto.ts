import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_MANUFACTURER_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Manufacturer Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'country',
          label: 'Country',
          type: 'TEXT',
          value: ''
        }

      ]
    },

    /*
     * MEDIA
     */
    {
      title: 'Media',

      fields: [

        {
          key: 'logo',
          label: 'Logo',
          type: 'IMAGE_UPLOAD',
          value: ''
        }

      ]
    }

  ]

};