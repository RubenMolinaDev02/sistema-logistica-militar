import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_GAS_MASK_FILTER_MODEL: ItemFormModel = {
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
      title: 'Specifications',
      fields: [
        {
          key: 'useHours',
          label: 'Use Hours',
          type: 'NUMBER',
          value: 0
        },

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