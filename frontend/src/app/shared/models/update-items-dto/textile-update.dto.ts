import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_TEXTILE_MODEL: ItemFormModel = {
  sections: [
    {
      title: 'Basic',
      fields: [
        {
          key: 'reference',
          label: 'Reference',
          type: 'TEXT',
          value: '',
          disabled: true
        },
        {
          key: 'name',
          label: 'Name',
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