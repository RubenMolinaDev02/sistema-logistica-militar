import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_PLATFORM_MODEL: ItemFormModel = {
  sections: [

    /*
     * BASIC INFO
     */
    {
      title: 'Basic Information',

      fields: [

        {
          key: 'name',
          label: 'Platform Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Platform Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        }

      ]
    },

    /*
     * DESCRIPTION
     */
    {
      title: 'Description',

      fields: [

        {
          key: 'description',
          label: 'Description',
          type: 'TEXT',
          value: ''
        }

      ]
    }

  ]
};