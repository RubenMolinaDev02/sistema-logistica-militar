import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_GAS_MASK_MODEL: ItemFormModel = {
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